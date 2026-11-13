package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"unicode"
	"unicode/utf8"
)

func main() {
	// Exercise 4.8
	// Count letters, digits, etc in an input file
	counts := make(map[rune]int)
	utflen := [utf8.UTFMax+1]int{}
	type RuneType uint8
	const (
		LETTER RuneType = iota
		DIGIT
		PUNCT
		OTHER
		TYPE_SIZE
	)
	typeCount := [TYPE_SIZE]int{}
	typeString := [TYPE_SIZE]string {"letter", "digit", "punct", "other"}

	in := bufio.NewReader(os.Stdin)
	for {
		r, n, err := in.ReadRune()
		if err == io.EOF {
			break
		}
		if err != nil {
			fmt.Printf("Bad input")
			os.Exit(1)
		}
		counts[r]++
		utflen[n]++
		switch {
		case unicode.IsLetter(r):
			typeCount[LETTER]++
		case unicode.IsDigit(r):
			typeCount[DIGIT]++
		case unicode.IsPunct(r):
			typeCount[PUNCT]++
		default:
			typeCount[OTHER]++
		}
	}

	fmt.Printf("rune\tcount\n")
	for c, n := range counts {
		fmt.Printf("%q\t%d\n", c, n)
	}

	fmt.Printf("len\tcount\n")
	for i, n := range utflen {
		if i > 0 {
			fmt.Printf("%d\t%d\n", i, n)
		}
	}

	fmt.Printf("type\tcount\n")
	for i, n := range typeCount {
		fmt.Printf("%s\t%d\n", typeString[i], n)
	}
}
