        .section .rodata
hello:
	.string "hello world\n"

        .text
        .globl	_start
	.type	_start, @function
_start:
	movq	$1, %rax        # __NR_write 1
	movq	$1, %rdi        # STDOUT_FILENO	1
	movq	$hello, %rsi
	movq	$12, %rdx
	syscall
	movq	$60, %rax       # __NR_exit 60
	movq	$10, %rdi       # exit value
	syscall	
	.size	_start, .-_start
