#include <stdio.h>

int globx = 0;

void foo() {
  globx++;
}

void bar() {
  printf("in %s\n", __func__);
}

#pragma init(foo)
#pragma fini(bar)

int main() {
  printf("in %s\n", __func__);
  return 0;
}
