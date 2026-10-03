#include <stdio.h>

static char global_buf[8];

int main(void) {
    for (int i = 0; i < 16; ++i) {
        global_buf[i] = 'A';  // writes past the end of global_buf
    }

    printf("%c\n", global_buf[0]);
    return 0;
}
