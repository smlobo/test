// Pass by reference
#include<stdio.h>

void swap(int &x, int &y) {
  int temp = x;
  x = y;
  y = temp;
}

void main() {
  int x = 10;
  int y = 20;

  printf("x = %d, y = %d\n", x, y);

  swap(x, y);

  printf("x = %d, y = %d\n", x, y);
}
