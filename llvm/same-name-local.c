#include <stdio.h>
#include <stdbool.h>

typedef struct {
    int a;
    bool b;
} someStruct;

void foo();

int main() {
    someStruct x = {10, true};
    printf("x.a = %d, x.b = %d\n", x.a, x.b);

    foo();

    return 0;
}
