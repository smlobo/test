// C program to demonstrate 
// working of setjmp() and
// longjmp()
#include <setjmp.h>
#include <stdio.h>
#include <strings.h>
#include <stdlib.h>

jmp_buf buf;

int i = 0;

void check_val(int *x)
{
    if (*x == 3) {
        // Jump to the point setup by setjmp
        printf("Got %d - ABORT\n", *x);
        longjmp(buf, 1);
    }
  
    printf("Got %d - sweet\n", *x);
}
  
int main(int argc, char **argv)
{
    // int i = 0;
    printf("Welcome to setjmp ... your input: %s\n", argv[1]);
    int j = atoi(argv[1]);

    // Setup jump position using buf and return 0
    setjmp(buf);
    printf("Starting after setjmp: %d\n", j);

    for (; i < j; i++) {
        check_val(&i);
    }
    printf("Done ...%s\n", argv[1]);

    return 0;
}
