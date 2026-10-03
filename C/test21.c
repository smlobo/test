// Integer greater than 32-bit; addition function

#include <stdio.h>

typedef struct bi {
  int h;
  unsigned int l;
} big_int;

big_int add(big_int x, big_int y) {
  big_int r;
  r.l = x.l + y.l;
  r.h = x.h + y.h;

  // Get the average of the lower part
  unsigned int avg_l = x.l/2 + y.l/2;
  if (x.l%2 && y.l%2)
    avg_l++;

  // Detect overflow
  if (avg_l >= 0x80000000)
     r.h++;

  return r;
}

int main() {
  big_int a = {0x10000000, 0x90000000};
  big_int b = {0x20000000, 0x70000000};

  big_int c = add(a, b);
  printf("a + b = %#.8x%.8x\n", c.h, c.l);

  return 0;
}

