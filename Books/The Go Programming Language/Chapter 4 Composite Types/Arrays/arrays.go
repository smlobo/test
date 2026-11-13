package main

import (
	"crypto/sha256"
	"crypto/sha512"
	"fmt"
)

func main() {
	var twoD [2][3]int
	for i := 0; i < len(twoD); i++ {
		for j := 0; j < len(twoD[i]); j++ {
			twoD[i][j] = i + j
		}
	}
	fmt.Println("2d: ", twoD)

	// Explicit indices
	type Currency int
	const (
		USD Currency = iota + 2
		EUR
		GBP
		INR
	)
	symbol := [...]string{USD: "$", INR: "₹", GBP: "£", EUR: ""}
	for index, value := range symbol {
		fmt.Printf("  [%d] %s\n", index, value)
	}

	// Array comparison - same size
	a := [2]int{1, 2}
	b := [...]int{1, 2}
	c := [2]int{1, 3}
	fmt.Println(a == b, a == c, b == c)
	//d := [3]int{1, 2}
	//fmt.Println(a == d)

	// SHA256 array comparison
	c1 := sha256.Sum256([]byte("x"))
	c2 := sha256.Sum256([]byte("X"))
	fmt.Printf("SHA256 'x': %v\n", c1)
	fmt.Printf("SHA256 'X': %v\n", c2)
	fmt.Printf("x == X ? %v\n", c1 == c2)
	fmt.Printf("Type of SHA256: %T\n", c1)

	// Exercise 4.1
	//fmt.Println("Different bits of 0x3 & 0x7: ", bitDifference(0x3, 0x7))
	//fmt.Println("Different bits of 0x4 & 0x8: ", bitDifference(0x4, 0x8))
	fmt.Println("Different bits of \"x\" to \"X\" : ", bitDifferenceSHA256(c1, c2))

	// Exercise 4.2
	fmt.Printf("SHA384 'X': %v\n", sha512.Sum384([]byte("X")))
	fmt.Printf("SHA512 'X': %v\n", sha512.Sum512([]byte("X")))
}

func bitDifferenceSHA256(x [32]uint8, y [32]uint8) int {
	var count int
	for index := 0; index < len(x); index++ {
		count += bitDifference(x[index], y[index])
	}
	return count
}

func bitDifference(x uint8, y uint8) int {
	var count int
	z := x ^ y
	//fmt.Printf("%x ^ %x = %x\n", x, y, z)
	for i := 0; i < 8; i++ {
		a := z & 0x1
		if a == 1 {
			count++
		}
		z >>= 1
	}
	return count
}