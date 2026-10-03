#include <stdio.h>

extern int counter;
void inc(void);

int main() {
    inc();
    printf("%d\n", counter);
}