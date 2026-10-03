// Power of 2
#include <stdio.h>
#include <stdlib.h>

int main(int argv, char **argc) {
  if (argv != 2) {
    printf("Error\n");
    return 1;
  }

  int x = atoi(argc[1]);
  printf("x = %d\n", x);

  if ((x & (x-1)) == 0)
    printf("is a power of 2\n");

  return 0;
}
