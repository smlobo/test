#include <stdio.h>

void accessAs2D(double x[8]) {
    printf("In accessAs2D (of: %p)\n", x);
    int rows = 2;
    int cols = 4;
    double (*xPrime)[4] = (double(*)[4]) x;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            printf("[%d][%d] %.1f, ", i, j, xPrime[i][j]);
        }
        printf("\n");
    }
}

int main() {
    printf("Created 1d double array:");
    int dim = 8;
    double q[8];
    for (int i = 0; i < 8; i++) {
        q[i] = (double) i + ((double)i)/10.0;
        printf("[%d] %.1f, ", i, q[i]);
    }
    printf("\n");
    accessAs2D(q);
}
