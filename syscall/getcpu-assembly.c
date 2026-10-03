#include <stdio.h>

extern int my_getcpu(unsigned *, unsigned *, void *);

int main() {
  printf("calling my_getcpu()\n");
  int c = 0;
  int n = 0;
  int s = my_getcpu(&c, &n, NULL);
  printf("my getcpu syscall: cpu = %d, node = %d, return = %d\n", c, n, s);
  return 10;
}
