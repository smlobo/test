#include <stdio.h>

void foo(int *ptr) {
    int content = *ptr;
    printf("foo: [%#lx] %d\n", ptr, content);
}

int main() {
    int i = 111;
    foo(&i);

    foo(0x0);

    return 0;
}