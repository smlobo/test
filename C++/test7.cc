// reference
#include<stdio.h>

int main() {
  int x = 10;
  int y = 20;
  int &z = x;

  printf("x = %d, y = %d, z = %d\n", x, y, z);

  z = 30;
  printf("x = %d, y = %d, z = %d\n", x, y, z);
  
  z = y;
  printf("x = %d, y = %d, z = %d\n", x, y, z);

  return 0;
}
