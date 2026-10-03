package main

import (
	"fmt"
)

func maxu1byte() uint8 {
	return ^uint8(0)
}

func max8byte() int64 {
	return ^int64(0)
}

func complex() complex128 {
	return -2 + 3i
}
func main() {
	fmt.Printf("maxu1byte: u %d/%#x; s %d\n", maxu1byte(), maxu1byte(), int8(maxu1byte()))
	fmt.Printf("max8byte: s %d; u %d/%x\n", max8byte(), uint64(max8byte()), uint64(max8byte()))
	fmt.Printf("complex: %g\n", complex())
}