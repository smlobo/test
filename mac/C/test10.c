// x to the power of 2 series
#include <stdio.h>

void main() {
  unsigned int x = 1;
  int i;
  printf("%u ", x);
  for (i=0; i<31; i++)
    printf("%u ", x<<=1);
  printf("\n");
}
