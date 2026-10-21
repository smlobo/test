	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 11, 0
	.globl	_foo                            ## -- Begin function foo
	.p2align	4, 0x90
_foo:                                   ## @foo
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movl	%edi, -4(%rbp)
	movl	%esi, -8(%rbp)
	movl	%edx, -12(%rbp)
	movl	$0, -16(%rbp)
	cmpl	$0, -4(%rbp)
	je	LBB0_2
## %bb.1:
	movl	-8(%rbp), %eax
	addl	-12(%rbp), %eax
	movl	%eax, -16(%rbp)
	jmp	LBB0_3
LBB0_2:
	movl	-8(%rbp), %eax
	subl	-12(%rbp), %eax
	movl	%eax, -16(%rbp)
LBB0_3:
	movl	-16(%rbp), %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
.subsections_via_symbols
