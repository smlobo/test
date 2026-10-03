#include <stdio.h>

short x = 0x3fff;
short y = 0x3f80;
short z = 0x3fc0;

int main() {
  printf("%x & %x : %x\n", x, y, x & y);  
  printf("%x & %x : %x\n", x, z, x & z);  

  return 0;
}

