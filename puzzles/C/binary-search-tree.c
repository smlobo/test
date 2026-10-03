/* Binary Search Tree */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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

TreeNode *put(TreeNode *node, char *name) {
    if (name == NULL)
        return NULL;

    if (node == NULL) {
        TreeNode *new_node = (TreeNode *) malloc(sizeof(TreeNode));
        new_node->name = name;
        new_node->left = NULL;
        new_node->right = NULL;
        new_node->count = 1;
        return new_node;
    }

    if (strcmp(name, node->name) < 0) {
        node->left = put(node->left, name);
    }
    else if (strcmp(name, node->name) > 0) {
        node->right = put(node->right, name);
    }

    // Update the subtree size
    node->count = 1;
    if (node->left != NULL) {
        node->count += node->left->count;
    }
    if (node->right != NULL) {
        node->count += node->right->count;
    }

    return node;
}

int size(TreeNode *node) {
    if (node != NULL)
        return node->count;
    return 0;
}

int height(TreeNode *node) {
    if (node == NULL)
        return 0;
    int left_height = height(node->left);
    int right_height = height(node->right);
    if (left_height > right_height)
        return left_height + 1;
    else
        return right_height + 1;
}

bool full(TreeNode *node) {
    if (node == NULL)
        return true;

    if ((node->left == NULL && node->right != NULL) || 
        (node->left != NULL && node->right == NULL))
        return false;

    if (!full(node->left) || !full(node->right))
        return false;

    return true;
}

char *min(TreeNode *node) {
    if (node == NULL)
        return NULL;
    char *min_name = min(node->left);
    if (min_name == NULL)
        return node->name;
    return min_name;
}


char *max(TreeNode *node) {
    if (node == NULL)
        return NULL;
    char *max_name = max(node->right);
    if (max_name == NULL)
        return node->name;
    return max_name;
}

void inordered(char *array[], int *index, TreeNode *node) {
    if (node == NULL)
        return;

    inordered(array, index, node->left);
    array[*index] = node->name;
    *index += 1;
    inordered(array, index, node->right);
}

char **inorder(TreeNode *node) {
    char **array = (char **) malloc(node->count * sizeof(char *));
    int index = 0;

    inordered(array, &index, node);

    return array;
}


void preordered(char **array, int *index, TreeNode *node) {
    if (node == NULL)
        return;

    // Root
    array[*index] = node->name;
    *index += 1;

    // Left
    preordered(array, index, node->left);
    // Right
    preordered(array, index, node->right);
}

char **preorder(TreeNode *node) {
    char **array = (char **) malloc(node->count * sizeof(char *));
    int index = 0;
    preordered(array, &index, node);
    return array;
}

void postordered(char **array, int *index, TreeNode *node) {
    if (node == NULL)
        return;

    postordered(array, index, node->left);
    postordered(array, index, node->right);
    array[*index] = node->name;
    *index += 1;
}

char **postorder(TreeNode *node) {
    char **array = (char **) malloc(node->count * sizeof(char *));
    int index = 0;
    postordered(array, &index, node);
    return array;
}

char *tree_floor(TreeNode *node, char *name) {
    if (node == NULL)
        return NULL;

    if (strcmp(name, node->name) < 0)
        return tree_floor(node->left, name);
    else if (strcmp(name, node->name) == 0)
        return node->name;

    char *right_candidate = tree_floor(node->right, name);
    if (right_candidate)
        return right_candidate;

    return node->name;
}

char *tree_ceiling(TreeNode *node, char *name) {
    if (node == NULL)
        return NULL;

    if (strcmp(name, node->name) > 0)
        return tree_ceiling(node->right, name);
    else if (strcmp(name, node->name) == 0)
        return node->name;

    char *left_candidate = tree_ceiling(node->left, name);
    if (left_candidate)
        return left_candidate;

    return node->name;
}

void free_memory(TreeNode *node) {
    if (node == NULL)
        return;

    free_memory(node->left);
    free_memory(node->right);

    free(node);
}

void ordered_traversal(char *name, char **array, int length) {
    printf("  %s: ", name);
    for (int i = 0; i < length; i++) {
        printf("%s, ", array[i]);
    }
    printf("\n");
    free(array);
}

int main(int argc, char **argv) {
    // Tree 1
    TreeNode *r1 = put(NULL, "D");
    printf("Tree 1 root {before}: ");
    print_node(r1);
    put(r1, "B");
    put(r1, "F");
    put(r1, "C");
    put(r1, "E");
    put(r1, "G");
    put(r1, "A");
    printf("Tree 1 root {final}: ");
    print_node(r1);
    printf("    Min: %s\n", min(r1));
    printf("    Max: %s\n", max(r1));
    printf("    Height: %d\n", height(r1));
    printf("    Full: %s\n", full(r1) ? "yes" : "no");

    ordered_traversal("Inorder", inorder(r1), size(r1));
    ordered_traversal("Preorder", preorder(r1), size(r1));
    ordered_traversal("Postorder", postorder(r1), size(r1));

    printf("    Floor B: %s\n", tree_floor(r1, "B"));
    printf("    Ceiling E: %s\n", tree_ceiling(r1, "E"));

    free_memory(r1);

    /*---------------------------------------------------*/
    // Tree 1
    TreeNode *r2 = put(NULL, "P");
    put(r2, "E");
    put(r2, "S");
    put(r2, "K");
    put(r2, "Y");
    put(r2, "D");
    put(r2, "U");
    put(r2, "D");
    put(r2, "E");
    put(r2, "T");
    put(r2, "W");
    put(r2, "O");
    printf("Tree 2 root: ");
    print_node(r2);
    printf("    Min: %s\n", min(r2));
    printf("    Max: %s\n", max(r2));
    printf("    Height: %d\n", height(r2));
    printf("    Full: %s\n", full(r2) ? "yes" : "no");

    ordered_traversal("Inorder", inorder(r2), size(r2));
    ordered_traversal("Preorder", preorder(r2), size(r2));
    ordered_traversal("Postorder", postorder(r2), size(r2));

    printf("    Floor H: %s\n", tree_floor(r2, "H"));
    printf("    Ceiling Q: %s\n", tree_ceiling(r2, "Q"));

    free_memory(r2);

    return 0;
}
