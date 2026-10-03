#include <stdio.h>
#include <stdlib.h>

struct DoubleLinkNode {
    char *name;
    struct DoubleLinkNode *next;
    struct DoubleLinkNode *prev;
};

struct DoubleLinkNode *generate_node();
char *generate_name();
void cleanup_node(struct DoubleLinkNode *node);

int main(int argc, char **argv) {
    printf("Enter list length: ");
    int length = 0;
    scanf("%d", &length);

    printf("You want a %d long list!\n", length);

    struct DoubleLinkNode *head = NULL;
    struct DoubleLinkNode *tail = NULL;
    struct DoubleLinkNode *pNode = NULL;
    struct DoubleLinkNode *cNode = NULL;
    for (int i = 0; i < length; i++) {
        cNode = generate_node();

        // First node
        if (head == NULL) {
            head = cNode;
        }
        else {
            pNode->next = cNode;
        }

        cNode->prev = pNode;
        pNode = cNode;
    }
    tail = cNode;

    // Print forward
    printf("F -> ");
    cNode = head;
    while (cNode != NULL) {
        printf("%s <-> ", cNode->name);
        cNode = cNode->next;
    }
    printf("\n");

    // Print backward
    printf("B -> ");
    cNode = tail;
    while (cNode != NULL) {
        printf("%s <-> ", cNode->name);
        cNode = cNode->prev;
    }
    printf("\n");

    // Cleanup
    cNode = head;
    while (cNode != NULL) {
        pNode = cNode;
        cNode = cNode->next;
        cleanup_node(pNode);
    }    

    return 0;
}

struct DoubleLinkNode *generate_node() {
    struct DoubleLinkNode *node = malloc(sizeof(struct DoubleLinkNode));
    node->name = generate_name();
    return node; 
}

char *generate_name() {
    char *name = malloc(4 * sizeof(char));
    for (int i = 0; i < 3; i++) {
        // Random number between 0 & 25
        int n = abs(rand()) % 26;
        name[i] = 'A' + (char) n;
    }
    name[3] = '\0';
    return name;
}

void cleanup_node(struct DoubleLinkNode *node) {
    free(node->name);
    free(node);
}
