	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 11, 0
	.globl	__Z1mbb                         ## -- Begin function _Z1mbb
	.p2align	4, 0x90
__Z1mbb:                                ## @_Z1mbb
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movb	%sil, %al 				## y
	movb	%dil, %cl				## r
	andb	$1, %cl
	movb	%cl, -1(%rbp)			## rbp-1 : r
	andb	$1, %al
	movb	%al, -2(%rbp)			## rbp-2 : y
	movb	$1, %al
	testb	$1, -2(%rbp)			## y == 1 ?
	movb	%al, -4(%rbp)           ## rbp-4 = 1        ## 1-byte Spill
	jne	LBB0_2
## %bb.1:
	movb	-1(%rbp), %al 			## y == 1 ; l == r
	movb	%al, -4(%rbp)                   ## 1-byte Spill
LBB0_2:
	movb	-4(%rbp), %al           ## y == 0        ## 1-byte Reload
	andb	$1, %al
	movb	%al, -3(%rbp)
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
.subsections_via_symbols
