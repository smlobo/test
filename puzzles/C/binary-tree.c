/* Binary Tree */

#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode {
    char *name;
    struct TreeNode *left;
    struct TreeNode *right;
    int count;
} TreeNode;

void print_node(TreeNode *node) {
    printf("%s -> (%s, %s) : %d\n", node->name, 
        (node->left == NULL) ? "<>" : node->left->name, 
        (node->right == NULL) ? "<>" : node->right->name, 
        node->count);
}

TreeNode *build_tree(char **heap, int length, int index) {
    // End of this branch
    if (index > length || heap[index] == NULL)
        return NULL;

    // The current node
    TreeNode *node = (TreeNode *) malloc(sizeof(TreeNode));
    node->name = heap[index];

    // Left subtree
    node->left = build_tree(heap, length, index*2);

    // Right subtree
    node->right = build_tree(heap, length, index*2+1);

    // Subtree size
    node->count = 1;
    if (node->left != NULL) {
        node->count += node->left->count;
    }
    if (node->right != NULL) {
        node->count += node->right->count;
    }

    print_node(node);

    return node;
}

int tree_size(TreeNode *node) {
    if (node != NULL)
        return node->count;
    return 0;
}

int main(int argc, char **argv) {
    // Tree 1
    char *th1[] = {"", "A", "B", "C", "D", "E", "F", "G"};
    TreeNode *r1 = build_tree(th1, 7, 1);
    printf("Tree 1 root: ");
    print_node(r1);

    // Tree 1
    char *th2[] = {"", "A", "B", NULL, "C", "D", NULL, NULL, NULL, "E", "F"};
    TreeNode *r2 = build_tree(th2, 10, 1);
    printf("Tree 2 root: ");
    print_node(r2);

    return 0;
}
