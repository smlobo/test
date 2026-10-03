// Reverse number - no array
#include <stdio.h>

int numrev(int n) {
  int x = 0;

  while (n != 0) {
    x = x*10 + n%10;
    n /= 10;
  }

  return x;
}

int main() {
  int i;
  scanf("%d", &i);

  printf("%d\n", i);
  i = numrev(i);
  printf("%d\n", i);
}

