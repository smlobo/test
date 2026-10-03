package main

import "fmt"

func main() {
	s := "Hello, 世界₹"

	// Indexing: raw bytes
	for i := 0; i < len(s); i++ {
		fmt.Printf("[%d] %x\n", i, s[i])      // 228 (first byte of '世')		
	}

	// Iterating: runes (characters)
	for i, r := range s {
	    fmt.Printf("%d: %c\n", i, r)
	}
}
