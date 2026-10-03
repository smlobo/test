// type conversion

#include <stdio.h>

int main() {
  int a = 3;
  float b = 2.7;
  float c = 1.4;
  long long int d = 4;

  int x = a + b + c;
  float y = a + b + c;
  
  printf("a+b = %d, a+b+c = %d\n", a+b, a+b+c);
  printf("a+b = %f, a+b+c = %f\n", a+b, a+b+c);
  printf("a+d (int) = %d\n", a+d);
  printf("a+d (long long)= %lld\n", a+d);
  printf("d+a (int) = %d\n", d+a);
  printf("d+a (long long)= %lld\n", d+a);
  printf("x = %d, y = %f\n", x, y);

  return 0;
}
