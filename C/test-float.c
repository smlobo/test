#include <stdio.h>
#include <math.h>
#include <float.h>

int main() {
    float a = 1.234;
    float b = 2.301;
    float c = a + b;
    printf("a = %.4f, b = %.4f, c = %.4f\n", a, b, c);
    printf("FLT_EPSILON = %.6f\n", FLT_EPSILON);
    float diff = fabsf(a + b - c);
    printf("diff = %.6f\n", diff);
}