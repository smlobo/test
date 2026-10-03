package main

import (
	"fmt"
)

func getNormal() []byte {
	return []byte {65, 66, 67}
}

func getEmpty() []byte {
	return []byte{}
}

func getNil() []byte {
	return nil
}

func main() {
	// Normal byte slice
	b1 := getNormal()
	fmt.Printf("Normal: %T, len: %d, cap :%d, %s, %s\n", b1, len(b1), cap(b1), b1, string(b1))

	// Empty byte slice
	b1 = getEmpty()
	fmt.Printf("Empty: %T, len: %d, cap :%d, %s, %s\n", b1, len(b1), cap(b1), b1, string(b1))
	if b1 != nil {
		fmt.Printf("  Empty is NOT nil\n")
	}

	// Nil byte slice
	b1 = getNil()
	fmt.Printf("Nil: %T, len: %d, cap :%d, %s, %s\n", b1, len(b1), cap(b1), b1, string(b1))
	if b1 == nil {
		fmt.Printf("  Nil is nil\n")
	}

	// Append byte slice
	b1 = append(b1, getNormal()...)
	b1 = append(b1, b1...)
	fmt.Printf("Append: %T, len: %d, cap :%d, %s, %s\n", b1, len(b1), cap(b1), b1, string(b1))
}