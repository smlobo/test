#include <stdlib.h>

int foo() {
  char *x = (char*)malloc(10 * sizeof(char*));
  return x[5];
}

int main() {
  return foo();
}

