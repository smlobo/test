package main

import (
	"fmt"
)

func nilByteSlice() []byte {
	return nil
}

func emptyByteSlice() []byte {
	return []byte{}
}

func notEmptyByteSlice() []byte {
	return []byte("foo")
}

func main() {
	n := nilByteSlice()
	e := emptyByteSlice()
	ne := notEmptyByteSlice()
	fmt.Printf("nil len: %d\n", len(n))
	fmt.Printf("empty len: %d\n", len(e))
	fmt.Printf("not empty len: %d\n", len(ne))

}