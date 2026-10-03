#include <stdio.h>

static int xxx;

int foo();

int bar() {
    return xxx++;
}

int main(int argc, char** argv) {
  for (int i = 0; i < 5; i++) {
    printf("[%d] foo() == %d\n", i, foo());
    printf("[%d] bar() == %d\n", i, bar());
  }

  return 0;
}

int foo() {
  static int xxx = 10;
  return xxx++;
}

