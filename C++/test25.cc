// Square root.

#include <stdio.h>

float sqroot(float x, float min=-10000, float max=10000) {
  if ((max - min) < 0.001)
    return min;

  float mean = (min + max)/2;

  printf("mean^2=%.2f, min=%2f, max=%2f\n", mean*mean, min, max);

  if (x > (mean*mean))
    return sqroot(x, mean+0.001, max);
  else
    return sqroot(x, min, mean-0.001);
}

int main() {
  float n;
  scanf("%f", &n);

  printf("Sq root of: %.4f = %.4f\n", n, sqroot(n));

  return 1;
}

  
