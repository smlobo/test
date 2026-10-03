// Allocate a 2D array
#include <stdio.h>
#include <stdlib.h>

int main() {
  int **a = (int **) malloc(sizeof(int) * 4 * 5);

  for (int i = 0; i<4; i++) {
    for (int j = 0; j<5; j++) {
      a[i][j] = i * j;
    }
  }

  for (int i = 0; i<4; i++) {
    for (int j = 0; j<5; j++) {
      printf("[%d][%d] %d\n", i, j, a[i][j]);
    }
  }

  return 0;
}

