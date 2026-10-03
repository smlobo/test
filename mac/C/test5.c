// Allocate a 2D array
#include <stdio.h>

int x = 10;
int y = 20;

int main() {
  int i, j;
  int **a = (int **) malloc(sizeof(int *) * x);
  for (i=0; i<x; i++) {
    *(a+i) = (int *) malloc(sizeof(int) * y);
  }

  for (i=0; i<10; i++) {
    for (j=0; j<y; j++) {
      a[i][j] = i+j;
    }
  }

  for (i=0; i<10; i++) {
    for (j=0; j<y; j++) {
      printf("%2d ", a[i][j]);
    }
    printf("\n");
  }

  return 0;
}
