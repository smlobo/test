	.file	"code-hoisting.c"
	.text
	.p2align 4,,15
	.globl	foo
	.type	foo, @function
foo:
.LFB0:
	.cfi_startproc
	addl	%esi, %edi
	testl	%edx, %edx
	jne	.L4
	jmp	bar2
	.p2align 4,,10
	.p2align 3
.L4:
	jmp	bar1
	.cfi_endproc
.LFE0:
	.size	foo, .-foo
	.ident	"GCC: (GNU) 6.2.1 20160916 (Red Hat 6.2.1-3)"
	.section	.note.GNU-stack,"",@progbits
