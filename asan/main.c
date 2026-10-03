#include <stdio.h>

void read_after_free();

int main() {
    printf("Calling read_after_free()\n");
    read_after_free();

    return 0;
}