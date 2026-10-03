// shm_server - write to shared memory. Wait for the client to indicate that 
// it has read it, detach, remove the shared memory, and quit.

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#define SHM_SIZE     27

int main(int argc, char **argv) {
    char c;
    int shmid;
    key_t key;
    char *shm, *s;

    if (argc != 2) {
        printf("Usage: shm_server <integer_key>\n");
        exit(-1);
    }

    // Read the key from the input arguments
    key = atoi(argv[1]);

    // Create the segment.
    if ((shmid = shmget(key, SHM_SIZE, IPC_CREAT | 0600)) == -1) {
        perror("shmget");
        exit(-1);
    }
    printf("server: shmget() created shared memory with id: %d\n", shmid);

    // Attach to the segment
    if ((shm = shmat(shmid, NULL, 0)) == (void *) -1) {
        perror("shmat");
        exit(-1);
    }
    printf("server: shmat() got address %#lx\n", shm);

    // Write to memory
    s = shm;
    for (c = 'a'; c <= 'z'; c++)
        *s++ = c;
    *s = '\0';
    printf("server: wrote to shm : %s\n", shm);

    // Wait until the other process changes the first character to '*', 
    // indicating that it has read what we put there.
    while (*shm != '*')
        sleep(1);

    printf("server: wake and goodbye\n");

    // Detach from the segment
    if (shmdt(shm) != 0) {
        perror("shmdt");
        exit(-1);
    }
    printf("server: shmdt() done\n");

    // Remove the shared memory
    if (shmctl(shmid, IPC_RMID, NULL) != 0) {
        perror("shmctl");
        exit(-1);
    }
    printf("server: shmctl(IPC_RMID) done\n");

    exit(0);
}
