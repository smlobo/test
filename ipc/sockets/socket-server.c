#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <stdbool.h>

#include "socket-common.h"

#define MAX_CONNECTIONS     8

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
        report("server socket", true);

    // bind the server's local address in memory
    struct sockaddr_in saddr;
    memset(&saddr, 0, sizeof(saddr));           // clear the bytes
    saddr.sin_family = AF_INET;                 // versus AF_LOCAL
    saddr.sin_addr.s_addr = htonl(INADDR_ANY);  // host-to-network endian
    saddr.sin_port = htons(port);               // for listening

    if (bind(fd, (struct sockaddr *) &saddr, sizeof(saddr)) == -1)
        report("server bind", true);

    // listen to the socket
    if (listen(fd, MAX_CONNECTIONS) == -1)
        report("server listen", true);

    fprintf(stderr, "Listening on port %d for clients...\n", port);
 
    // a server traditionally listens indefinitely
    while (true) {
        struct sockaddr_in caddr;   // client address
        int len = sizeof(caddr);

        int client_fd = accept(fd, (struct sockaddr*) &caddr, &len);
        if (client_fd == -1) {
            report("server accept", false);
            continue;
        }
        printf("  Got connection from addr: %s, port: %d\n", 
            inet_ntoa(caddr.sin_addr), caddr.sin_port);

        // read from client
        //for (int i = 0; i < ConversationLen; i++) {
        while (true) {
            char buffer[BUFF_SIZE + 1];
            memset(buffer, '\0', sizeof(buffer));
            int count = read(client_fd, buffer, sizeof(buffer));
            if (count > 0) {
                printf("    -> Client sent: %s\n", buffer);

                // Client closing connection
                if (strcmp(buffer, CLOSE_STRING) == 0)
                    break;
                write(client_fd, buffer, sizeof(buffer));
            }
        }

        // close the connection
        printf("  Closing connection with addr: %s, port: %d\n", 
            inet_ntoa(caddr.sin_addr), caddr.sin_port);
        close(client_fd);
    }

    return 0;
}
