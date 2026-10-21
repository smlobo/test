// C program to demonstrate 
// working of setjmp() and
// longjmp()
#include <setjmp.h>
#include <stdio.h>
#include <strings.h>
#include <stdlib.h>

jmp_buf buf;

void check_input(char *x)
{
    if (!strcmp(x, "bad")) {
        // Jump to the point setup by setjmp
        printf("Oh oh ... %s\n", x);
        longjmp(buf, 1);
    }
  
    printf("Done checking ... %s\n", x);
}
  
int main(int argc, char **argv)
{
    printf("Welcome to setjmp ... your input: %s\n", argv[1]);

    // Setup jump position using buf and return 0
    if (setjmp(buf)) {
        printf("Bad input: %s\n", argv[1]);
        exit(1);
    }

    printf("Checking your input ...\n");
    check_input(argv[1]);
    printf("Input looking good ...\n");

    return 0;
}
