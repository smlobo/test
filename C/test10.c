// Reverse string
#include <stdio.h>

void strrev(char *x) {
  char *y = x + strlen(x) - 1;
  
  while (x<y) {
    char t = *x;
    *x = *y;
    *y = t;
    x++;
    y--;
  }
}

int main() {
  char a[100];

  scanf("%s", a);

  printf("%s [%d]\n", a, strlen(a));

  strrev(a);
  printf("%s [%d]\n", a, strlen(a));
}

