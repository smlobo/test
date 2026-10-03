	.file	"attr-preinit-array.c"
	.section	.rodata
.LC0:
	.string	"in %s\n"
	.text
	.globl	foo
	.type	foo, @function
foo:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$__func__.2210, %esi
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	nop
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	foo, .-foo
	.section	.init_array,"aw"
	.align 8
	.quad	foo
	.text
	.type	bar1, @function
bar1:
.LFB1:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$__func__.2213, %esi
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	nop
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE1:
	.size	bar1, .-bar1
	.type	bar2, @function
bar2:
.LFB2:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$__func__.2216, %esi
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	nop
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	bar2, .-bar2
	.section	.preinit_array,"aw"
	.align 16
	.type	y, @object
	.size	y, 16
y:
	.quad	bar1
	.quad	bar2
	.text
	.globl	main
	.type	main, @function
main:
.LFB3:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	$__func__.2220, %esi
	movl	$.LC0, %edi
	movl	$0, %eax
	call	printf
	movl	$0, %eax
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE3:
	.size	main, .-main
	.section	.rodata
	.type	__func__.2210, @object
	.size	__func__.2210, 4
__func__.2210:
	.string	"foo"
	.type	__func__.2213, @object
	.size	__func__.2213, 5
__func__.2213:
	.string	"bar1"
	.type	__func__.2216, @object
	.size	__func__.2216, 5
__func__.2216:
	.string	"bar2"
	.type	__func__.2220, @object
	.size	__func__.2220, 5
__func__.2220:
	.string	"main"
	.ident	"GCC: (GNU) 6.2.1 20160916 (Red Hat 6.2.1-3)"
	.section	.note.GNU-stack,"",@progbits
