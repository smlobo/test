	.file	"write-syscall.c"
	.section	.rodata
.LC0:
	.string	"Hello World\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$32, %rsp
	movl	%edi, -20(%rbp)
	movq	%rsi, -32(%rbp)
	movq	$.LC0, -8(%rbp)
	movq	-8(%rbp), %rax
	movl	$12, %edx
	movq	%rax, %rsi
	movl	$1, %edi
	movl	$0, %eax
	call	syscall
	movl	$10, %esi
	movl	$60, %edi
	movl	$0, %eax
	call	syscall
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (GNU) 4.8.5 20150623 (Red Hat 4.8.5-11)"
	.section	.note.GNU-stack,"",@progbits
