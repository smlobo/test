CC = cc
CFLAGS = -std=c11 -O2 -Wall -Wextra -Wpedantic -fPIC

ifeq ($(shell uname -s),Darwin)
LIBRARY = libcounterdemo.dylib
SHARED_FLAG = -dynamiclib
else
LIBRARY = libcounterdemo.so
SHARED_FLAG = -shared
endif

.PHONY: all test clean

all: counter_test

counter.o: counter.c counter.h
	$(CC) $(CFLAGS) -c $< -o $@

library.o: library.c counter.h library.h
	$(CC) $(CFLAGS) -c $< -o $@

main.o: main.c counter.h library.h
	$(CC) $(CFLAGS) -c $< -o $@

$(LIBRARY): counter.o library.o
	$(CC) $(SHARED_FLAG) $^ -o $@

counter_test: main.o counter.o $(LIBRARY)
	$(CC) main.o counter.o -L. -lcounterdemo -o $@

test: counter_test
	DYLD_LIBRARY_PATH=. LD_LIBRARY_PATH=. ./counter_test

clean:
	$(RM) counter.o library.o main.o counter_test libcounterdemo.dylib libcounterdemo.so
