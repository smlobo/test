#include <stdio.h>
#include <stdlib.h>

int main() {
    int **a2d = (int**) malloc(sizeof(int*)*3);
    for (int i = 0; i < 3; i++)
        a2d[i] = (int*) malloc(sizeof(int)*4);

    int count = 0;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 4; j++)
            a2d[i][j] = count++;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++)
            printf("%d ", a2d[i][j]);
        printf("\n");    
    }

    for (int i = 0; i < 3; i++)
        free(a2d[i]);
    free(a2d);
}