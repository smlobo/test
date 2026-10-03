#include <stdio.h>
#include <assert.h>
#include <stdlib.h>

int foo(int x, int y) {
  int ret = x + y;
  printf("{ %d + %d } = %d\n", x, y, ret);
  return ret;
}

int main(int argc, char **argv) {
  assert(argc == 3);
  int p = atoi(argv[1]);
  int q = atoi(argv[2]);
  printf("p: %d, q: %d\n", p, q);
  printf("foo: %d\n", foo(p, q));

  return 0;
}
