#include <stdio.h>

// CMU 15122, Final S15 7.1 2

int main() {
  unsigned int x = 0xffffffff;
  signed short y = 0x1;
  //signed short y = -1;
  unsigned int z = (unsigned int)(signed int) y;

  unsigned int k = x + z;
  printf("x = %#x, y = %#x, z = %#x, k = %#x\n", x, y, z, k);

  if (x + z < x)
    y = 0xbad;

  printf("[2] x = %#x, y = %#x, z = %#x, k = %#x\n", x, y, z, k);
}

