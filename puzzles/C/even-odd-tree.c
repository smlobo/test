#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/**
 * Definition for a binary tree node.
 */
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

int decompose(int heap[], struct TreeNode *node, int index, int depth) {
    int leftIndex = index * 2;
    int rightIndex = leftIndex + 1;

    int leftDepth = depth;
    if (node->left != NULL) {
        heap[leftIndex] = node->left->val;
        leftDepth = decompose(heap, node->left, leftIndex, depth+1);
    }
    int rightDepth = depth;
    if (node->right != NULL) {
        heap[rightIndex] = node->right->val;
        rightDepth = decompose(heap, node->right, rightIndex, depth+1);
    }
    if (leftDepth > rightDepth)
        return leftDepth;
    return rightDepth;
}

bool isEvenOddTree(struct TreeNode* root){
    
    // Avoid computation for an invalid root - depth 0
    if (root->val%2 == 0)
        return false;
    
    // decompose the tree into a binary heap
    // initialize the heap
    int *heap = malloc(10001 * sizeof(int));
    for (int i = 0; i < 10001; i++)
        heap[i] = -1;

    // Decompose it - returning the max depth
    heap[1] = root->val;
    int maxDepth = decompose(heap, root, 1, 0);
    
    // Iterate over depths (no need to check depth 0)
    int twoPower = 1;
    for (int i = 1; i <= maxDepth; i++) {
        twoPower *= 2;
        int startIndex = twoPower;
        int endIndex = startIndex*2 - 1;
        
        // Odd level
        if (i%2 == 1) {
            int previous = 1000001;
            for (int j = startIndex; j <= endIndex; j++) {
                // Invalid value
                if (heap[j] == -1)
                    continue;
                // Value must be even
                if (heap[j]%2 == 1)
                    return false;
                // Decreasing order
                if (heap[j] >= previous)
                    return false;
                previous = heap[j];
            }
        }
        // Even level
        if (i%2 == 0) {
            int previous = 0;
            for (int j = startIndex; j <= endIndex; j++) {
                // Invalid value
                if (heap[j] == -1)
                    continue;
                // Value must be odd
                if (heap[j]%2 == 0)
                    return false;
                // Increasing order
                if (heap[j] <= previous)
                    return false;
                previous = heap[j];
            }
        }
    }
    
    free(heap);
    return true;
}

struct TreeNode *buildTree(int t[], int length) {
    struct TreeNode *root = malloc(sizeof(struct TreeNode));
    root->val = t[0];
    
    for (int i = 1; i < length; i++) {

    }
    return root;
}

int main(int argc, char **argv) {
    // Test 1
    int t1[] = {1,10,4,3,-1,7,9,12,8,6,-1,-1,2};
    struct TreeNode *r1 = buildTree(t1, 13);
}