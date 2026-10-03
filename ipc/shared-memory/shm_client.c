// shm-client - client program to demonstrate shared memory.
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int shmid;
    key_t key;
    char *shm, *s;

    if (argc != 2) {
        printf("Usage: shm_client <integer_key>\n");
        exit(-1);
    }

    // Read the key from the input arguments
    key = atoi(argv[1]);

    // Locate the segment.
    if ((shmid = shmget(key, 0, 0660)) == -1) {
        perror("shmget failed");
        exit(-1);
    }
    printf("client: shmget() retrieved shared memory with id: %d\n", shmid);

    // Attach to the segment
    if ((shm = shmat(shmid, NULL, 0)) == (char *) -1) {
        perror("shmat");
        exit(-1);
    }
    printf("client: shmat() got address %#lx\n", shm);

    // Read what the server put in the memory.
    for (s = shm; *s != '\0'; s++)
        putchar(*s);
    putchar('\n');

    // Change the first character to '*', indicating we are done reading.
    *shm = '*';
    printf("client: put control char '*' in shared memory - goodbye\n");

    // Detach from the segment
    if (shmdt(shm) != 0) {
        perror("shmdt");
        exit(-1);
    }
    printf("client: shmdt() done\n");

    exit(0);
}

