	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 11, 0	sdk_version 12, 1
	.globl	_addu1                          ## -- Begin function addu1
	.p2align	4, 0x90
_addu1:                                 ## @addu1
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movb	%sil, %al
	movb	%dil, %cl
	movb	%cl, -1(%rbp)
	movb	%al, -2(%rbp)
	movzbl	-1(%rbp), %eax
	movzbl	-2(%rbp), %ecx
	addl	%ecx, %eax
                                        ## kill: def $al killed $al killed $eax
	movzbl	%al, %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	_adds4                          ## -- Begin function adds4
	.p2align	4, 0x90
_adds4:                                 ## @adds4
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movl	%edi, -4(%rbp)
	movl	%esi, -8(%rbp)
	movl	-4(%rbp), %eax
	addl	-8(%rbp), %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	_derefInt                       ## -- Begin function derefInt
	.p2align	4, 0x90
_derefInt:                              ## @derefInt
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movq	%rdi, -8(%rbp)
	movq	-8(%rbp), %rax
	movl	(%rax), %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	_derefUInt                      ## -- Begin function derefUInt
	.p2align	4, 0x90
_derefUInt:                             ## @derefUInt
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movq	%rdi, -8(%rbp)
	movq	-8(%rbp), %rax
	movl	(%rax), %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	_derefStruct                    ## -- Begin function derefStruct
	.p2align	4, 0x90
_derefStruct:                           ## @derefStruct
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movq	%rdi, -24(%rbp)
	movq	-24(%rbp), %rax
	movl	8(%rax), %ecx
	movl	%ecx, -8(%rbp)
	movq	(%rax), %rax
	movq	%rax, -16(%rbp)
	movl	-8(%rbp), %eax
	movl	%eax, -32(%rbp)
	movq	-16(%rbp), %rax
	movq	%rax, -40(%rbp)
	movq	-40(%rbp), %rax
	movl	-32(%rbp), %edx
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	_structMembers                  ## -- Begin function structMembers
	.p2align	4, 0x90
_structMembers:                         ## @structMembers
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movq	%rdi, -8(%rbp)
	movq	-8(%rbp), %rax
	movl	8(%rax), %eax
	movq	-8(%rbp), %rcx
	addl	4(%rcx), %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	_arrayStructMembers             ## -- Begin function arrayStructMembers
	.p2align	4, 0x90
_arrayStructMembers:                    ## @arrayStructMembers
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movq	%rdi, -8(%rbp)
	movl	%esi, -12(%rbp)
	movq	-8(%rbp), %rax
	movslq	-12(%rbp), %rcx
	imulq	$12, %rcx, %rcx
	addq	%rcx, %rax
	movl	4(%rax), %eax
	movq	-8(%rbp), %rcx
	movslq	-12(%rbp), %rdx
	imulq	$12, %rdx, %rdx
	addq	%rdx, %rcx
	addl	8(%rcx), %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	_controlFlow                    ## -- Begin function controlFlow
	.p2align	4, 0x90
_controlFlow:                           ## @controlFlow
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movl	%edi, -8(%rbp)
	movl	%esi, -12(%rbp)
	movl	-8(%rbp), %eax
	cmpl	-12(%rbp), %eax
	jle	LBB7_2
## %bb.1:
	movl	-8(%rbp), %eax
	movl	%eax, -4(%rbp)
	jmp	LBB7_3
LBB7_2:
	movl	-12(%rbp), %eax
	movl	%eax, -4(%rbp)
LBB7_3:
	movl	-4(%rbp), %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
.subsections_via_symbols
