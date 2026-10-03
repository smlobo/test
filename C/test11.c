// lower to upper string
#include <stdio.h>

void l2u(char *x) {
  int d = 'a' - 'A';
  int l = 'a';
  int n = strlen(x);

  for (int i = 0; i < n; i++) {
    if (*x >= l)
      *x -= d;
    x++;
  }
}

int main() {
  char a[100];

  scanf("%s", a);

  printf("%s [%d]\n", a, strlen(a));

  l2u(a);
  printf("%s [%d]\n", a, strlen(a));
}

