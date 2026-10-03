#include <asm/unistd_64.h>

        .text
        .globl	my_getcpu
	.type	my_getcpu, @function
my_getcpu:
	movq	$__NR_getcpu, %rax
	syscall
        ret
	.size	my_getcpu, .-my_getcpu
