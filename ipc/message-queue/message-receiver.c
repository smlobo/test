#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>

#include "message-queue.h"

int main() {
    key_t key;
    int qid;

    key = ftok(PATH_NAME, ID);
    if (key < 0) {
        perror("couldn't get key ...");
        exit(-1);
    }
    printf("receiver: ftok() created key: %d\n", key);

    if ((qid = msgget(key, 0666 | IPC_CREAT)) == -1) {
        perror("couldn't get queue id ...");
        exit(-1);
    }
    printf("receiver: msgget() found queue: %d\n", qid);

    int types[] = {3, 1, 2, 1, 3, 2}; /* different than in sender */
    for (int i = 0; i < MSG_COUNT; i++) {
        QueuedMessage msg;
        if (msgrcv(qid, &msg, sizeof(msg), types[i], MSG_NOERROR | IPC_NOWAIT) == -1) {
            perror("receiver: couldn't receive ...");
            exit(-1);
        }
        printf("  %s received as type %ld\n", msg.payload, msg.type);
    }

    // remove the queue
    if (msgctl(qid, IPC_RMID, NULL) != 0) {
        perror("trouble removing queue...");
        exit(-1);
    }

    return 0;
}
