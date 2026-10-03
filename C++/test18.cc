// type conversion

#include <stdio.h>

int main() {
  int a = 3;
  float b = 2.7;
  float c = 1.4;

  int x = a + b + c;
  float y = a + b + c;
  
  printf("a+b = %d, a+b+c = %d\n", a+b, a+b+c);	// junk
  printf("a+b = %f, a+b+c = %f\n", a+b, a+b+c);	// 5.7, 7.1
  printf("x = %d, y = %f\n", x, y);		// 7, 7.1

  return 0;
}
