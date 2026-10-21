#include <stdio.h>
#include <stdbool.h>

void foo() {
    typedef struct {
        int a;
        float b;
    } someStruct;

    someStruct x = {20, 3.14};
    printf("foo(): x.a = %d, x.b = %.2f\n", x.a, x.b);
}
