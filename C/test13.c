// Function pointer array
#include <stdio.h>

void foo();
void bar();
void zoo();

int n = 2;
typedef void (*fptr)();
fptr x[] = {foo, bar, zoo};

int moo() {
  (*x[n])();

  return 0;
}

void foo() {
  printf("foo() - void\n");
}

void bar() {
  printf("bar() - int\n");
}

void zoo() {
  printf("zoo() - float\n");
}
