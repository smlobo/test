#include <stdio.h>

__attribute__((constructor)) void foo() {
  printf("in %s\n", __func__);
}

__attribute__((destructor)) void bar() {
  printf("in %s\n", __func__);
}

int main() {
  printf("in %s\n", __func__);
  return 0;
}
