package main

import (
	"fmt"
	"strconv"
)

func main() {
	// Rune is Unicode (uint32)
	// Go uses UTF-8 encoding. UTF-8 is a variable length encoding of Unicode

	greekString := "αβ"
	fmt.Println("Hello " + greekString)
	fmt.Println("  " + greekString + " string length: " + strconv.Itoa(len(greekString)))
	greekRuneSlice := []rune(greekString)
	fmt.Printf("  Rune slice: %x\n", greekRuneSlice)
	for i, r := range greekString {
		fmt.Printf("  [%d] %d %#04[2]x %[2]q\n", i, r)
	}
	fmt.Printf("  Unicode escape: %s == \u03b1\u03b2\n", greekString)
	greekByteSlice := []byte(greekString)
	fmt.Printf("  Byte slice: %# x\n", greekByteSlice)
	fmt.Printf("  Hex escape (UTF-8): %s == \xce\xb1\xce\xb2\n", greekString)

	hindiString := "राम"
	fmt.Println("Namaste " + hindiString)
	fmt.Println("  " + hindiString + " string length: " + strconv.Itoa(len(hindiString)))
	hindiRuneSlice := []rune(hindiString)
	fmt.Printf("  %x\n", hindiRuneSlice)
	for i, r := range hindiString {
		fmt.Printf("  [%d] %d %#04[2]x %[2]q\n", i, r)
	}
	fmt.Printf("  Unicode escape: %s == \u0930\u093e\u092e\n", hindiString)
	hindiByteSlice := []byte(hindiString)
	fmt.Printf("  Byte slice: %# x\n", hindiByteSlice)
	fmt.Printf("  Hex escape (UTF-8): %q == \xe0\xa4\xae\n", hindiRuneSlice[2])
}
