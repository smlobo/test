# Makefile for shared-memory

#CC = cc
CC = gcc

DEBUG = -g

#FLAGS = -O -std=c99
FLAGS = -std=c99

DEFINES = -D_SVID_SOURCE

%:%.c
	$(CC) $(DEBUG) $(FLAGS) $(DEFINES) -o $@ $@.c

default: shm_server shm_client

clean:
	rm -rf shm_server shm_client
