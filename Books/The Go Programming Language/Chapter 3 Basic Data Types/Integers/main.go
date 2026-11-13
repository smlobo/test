package main

import "fmt"

func main() {
	fmt.Println("Hello Go!")

	// Basic integers and operations
	var x int32 = 10
	var y int64 = 10001
	fmt.Printf("x = %d / %[1]x / %#[1]X\n", x)
	fmt.Printf("y = %d / %[1]x / %#[1]X\n", y)
}
