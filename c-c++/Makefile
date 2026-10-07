CC = clang
CXX = clang++
CFLAGS = -std=c11 -O2 -Wall -Wextra -Wpedantic -pthread
CXXFLAGS = -std=c++20 -O2 -Wall -Wextra -Wpedantic -pthread

.PHONY: all run clean

all: c_examples cpp_examples

c_examples: c_examples.c
	$(CC) $(CPPFLAGS) $(CFLAGS) $< $(LDFLAGS) $(LDLIBS) -o $@

cpp_examples: cpp_examples.cpp
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) $< $(LDFLAGS) $(LDLIBS) -o $@

run: all
	./c_examples
	./cpp_examples

clean:
	$(RM) c_examples cpp_examples
