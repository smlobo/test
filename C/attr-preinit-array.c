#include <stdio.h>

__attribute__((constructor)) void foo() {
  printf("in %s\n", __func__);
}

static void bar1() {
  printf("in %s\n", __func__);
}

static void bar2() {
  printf("in %s\n", __func__);
}

__attribute__((section(".preinit_array"))) static void *y[] = { &bar1, &bar2 };

int main() {
  printf("in %s\n", __func__);
  return 0;
}
