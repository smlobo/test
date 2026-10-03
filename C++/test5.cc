// fibo
#include<stdio.h>

void fibonacci(int i, int &c, int a, int b) {
  if (i == c)
    return;

  c++;
  printf("%d, ", a + b);

  fibonacci(i, c, b, a+b);
}

void main() {
  int i;
  scanf("%d", &i);
  printf("%d\n", i);

  int c = 2;

  if (i >= 0)
    printf("fibo : ");
  if (i >= 1)
    printf("1, ");
  if (i >= 2)
    printf("1, ");
  if (i >= 3)
    fibonacci(i, c, 1, 1);
  printf("\n");
}
