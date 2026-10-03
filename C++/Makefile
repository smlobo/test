# Makefile for C++ programs

CCC = c++

DEBUG = -g
#DEBUG = -v
#DEBUG = -v -keeptmp

%:%.cc
	$(CCC) $(DEBUG) -o $@ $@.cc

clean:
	rm -f a.out
	rm -f *.o
	rm -rf *.dSYM

