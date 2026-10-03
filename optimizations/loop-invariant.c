#include <stdio.h>

int x = 10;

int main() {
  for (int i = 0; i < 10; i++) {
    printf("%d -> %d\n", i, i+x);
  }

  return 0;
}
