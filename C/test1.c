#include<stdio.h>

int main() {
  int a = 1;
  for (int i = 1; i <= 32; i++) {
    a <<= 1;
    printf("[%d] a = %d, %#x\n", i, a, a);
  }
}
