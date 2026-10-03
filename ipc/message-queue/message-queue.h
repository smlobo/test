#define ID          5678
#define PATH_NAME    "message-queue.h"
#define MSG_LEN     4
#define MSG_COUNT   6

typedef struct {
    long type;                  /* must be of type long */
    char payload[MSG_LEN + 1];  /* bytes in the message */
} QueuedMessage;
