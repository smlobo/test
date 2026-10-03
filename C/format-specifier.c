#include <stdio.h>
#include <string.h>

const char *xxx = "I am %d long";

int main() {
  printf("global str: %s\n", xxx);
  printf("strlen: %lu\n", strlen(xxx));
  return 0;
}

