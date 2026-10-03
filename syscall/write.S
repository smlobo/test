//#include <unistd.h>
#include <asm/unistd_64.h>
        
        .section .rodata
hello:
	.string "hello world\n"

        .text
        .globl	main
	.type	main, @function
main:
	movq	$__NR_write, %rax
	movq	$1, %rdi        # STDOUT_FILENO	1 (cannot use unistd.h)
	movq	$hello, %rsi
	movq	$12, %rdx
	syscall
	movq	$__NR_exit, %rax
	movq	$10, %rdi       # exit value
	syscall	
	.size	main, .-main
