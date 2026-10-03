#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

int main() {
  int8_t *A = malloc(sizeof(int8_t)*256*256);
  int index = 0;
  for (int i = -128; i <= 127; i++) {
    for (int j = -128; j <= 127; j++) {
      A[index++] = (int8_t)i * (int8_t)j;
      printf("[%d][%d] index = %d\n", i, j, index);
    }
  }
  printf("index = %d\n", index);
  for (int i = 0; i < index; i++) {
    printf("A[%d] = %d\n", i, A[i]);
  }
}

