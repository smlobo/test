//go:build ignore

package main

import "fmt"

func main() {
	s := "abcø₹"
	b := []byte(s)
	for i, bb := range b {
		fmt.Printf("[%d] bb: %x\n", i, bb)
	}
	r := []rune(s)
	for i, rr := range r {
		fmt.Printf("[%d] rr: %x {%c}\n", i, rr, rr)
	}
}
