# Makefile for message-queue

#CC = cc
CC = gcc

DEBUG = -g

#FLAGS = -O -std=c99
FLAGS = -std=c99

DEFINES = -D_SVID_SOURCE

%:%.c
	$(CC) $(DEBUG) $(FLAGS) $(DEFINES) -o $@ $@.c

default: message-sender message-receiver

clean:
	rm -rf message-sender message-receiver
