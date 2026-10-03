#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <netdb.h>
#include <stdbool.h>

#include "socket-common.h"

const char* books[] = {"War and Peace",
                       "Pride and Prejudice",
                       "The Sound and the Fury"};

void report(const char* msg, bool terminate) {
    perror(msg);
    if (terminate)
        exit(-1);
}

int main(int argc, char **argv) {
    if (argc != 2) {
        printf("Usage: socket-server <port>\n");
        exit(-1);
    }

    // Read the port from the input arguments
    int port = atoi(argv[1]);
  
    int fd = socket(AF_INET,        // vs AF_LOCAL
                    SOCK_STREAM,    // reliable, bidirectional, arbitrary payload 
                    0);             // system picks underlying protocol (TCP)
    if (fd == -1)
        report("client socket", true);

    // connect to the server: configure server's address 1st
    struct sockaddr_in saddr;
    memset(&saddr, 0, sizeof(saddr));
    saddr.sin_family = AF_INET;
    saddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    saddr.sin_port = htons(port);

    if (connect(fd, (struct sockaddr*) &saddr, sizeof(saddr)) == -1)
        report("client connect", true);

    // Write some stuff and read the echoes
    printf("Connected on port %d, write to stdin, end with Ctrl-D...\n", port);
    char stdinBuffer[BUFF_SIZE + 1];
    //for (int i = 0; i < ConversationLen; i++) {
    while (true) {
        // Read from stdin
        memset(stdinBuffer, '\0', sizeof(stdinBuffer));
        scanf("%s", stdinBuffer);

        if (feof(stdin))
            break;

        if (write(fd, stdinBuffer, strlen(stdinBuffer)) > 0) {
            // get confirmation echoed from server and print
            char buffer[BUFF_SIZE + 1];
            memset(buffer, '\0', sizeof(buffer));
            if (read(fd, buffer, sizeof(buffer)) > 0)
                printf("    -> Server replied: %s\n", buffer);
        }
    }

    // Tell the server we're closing the connection
    write(fd, CLOSE_STRING, strlen(CLOSE_STRING)+1);
    printf("Close string \"%s\" sent. Close the connection and exit.\n", 
        CLOSE_STRING);
    close(fd);
    
    return 0;
}
