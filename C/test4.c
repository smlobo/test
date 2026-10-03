// factorial
#include<stdio.h>

int factorial(int x) {
  int t = 0;
  if (x != 0)
    t = factorial(x-1);
  else
    return 1;
  return x * t;
}

void main() {
  int i;
  scanf("%d", &i);
  printf("%d\n", i);

  printf("factorial : %d\n", factorial(i));
}
