# Makefile for C programs

CC = cc

DEBUG = -g
#DEBUG = -v
#DEBUG = -v -keeptmp

%:%.c
	$(CC) $(DEBUG) -o $@ $@.c

clean:
	rm -f a.out
	rm -f *.o
	rm -rf *.dSYM

