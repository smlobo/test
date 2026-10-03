// Ascii to integer
#include <stdio.h>
#include <stdlib.h>

int main(int argv, char **argc) {
  if (argv != 2) {
    printf("Error\n");
    return 1;
  }

  int x = atoi(argc[1]);
  printf("converted to: %d\n", x);
}
