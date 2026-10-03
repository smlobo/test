# Makefile for sockets

#CC = cc
CC = gcc

DEBUG = -g

#FLAGS = -O -std=c99
FLAGS = -std=c99

#DEFINES = -D_SVID_SOURCE

%:%.c
	$(CC) $(DEBUG) $(FLAGS) $(DEFINES) -o $@ $@.c

default: socket-server socket-client

clean:
	rm -rf socket-server socket-client
