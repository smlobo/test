#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>
#include <string.h>

#include "message-queue.h"

int main() {
    key_t key;
    int qid;


    key = ftok(PATH_NAME, ID);
    if (key < 0) {
        perror("sender: couldn't get key ...");
        exit(-1);
    }
    printf("sender: ftok() created key: %d\n", key);

    if ((qid = msgget(key, 0666 | IPC_CREAT)) == -1) {
        perror("sender: couldn't get queue id ...");
        exit(-1);
    }
    printf("sender: msgget() created queue: %d\n", qid);

    char* payloads[] = {"msg1", "msg2", "msg3", "msg4", "msg5", "msg6"};
    int types[] = {1, 1, 2, 2, 3, 3}; /* each must be > 0 */
    for (int i = 0; i < MSG_COUNT; i++) {
        /* build the message */
        QueuedMessage msg;
        msg.type = types[i];
        strcpy(msg.payload, payloads[i]);

        /* send the message - don't block */
        if (msgsnd(qid, &msg, sizeof(msg), IPC_NOWAIT) == -1) {
            perror("sender: couldn't sent ...");
            exit(-1);
        }
        printf("  %s sent as type %ld\n", msg.payload, msg.type);
    }

    return 0;
}
