package main

import "fmt"

// 3 places where ellipsis (...) are seen in Go:
// [1] variadic function parameters
// [2] unpack a slice as an argument to a variadic function
// [3] an array literal

// Variadic function parameter
func sum(nums ...int) int {
	var answer int
	for _, num := range nums {
		answer += num
	}
	return answer
}

func main() {
	fmt.Println("Sum 1, 2, 3: ", sum(1, 2, 3))

	// Arguments to variadic functions
	mySlice := [] int {4, 5}
	fmt.Println("Sum 4, 5: ", sum(mySlice...))

	// Array literal
	myArray := [...] int {1, 2}
	// fmt.Println("Sum 1, 2: ", sum(myArray...))
	myArraySlice := myArray[:]
	fmt.Println("Sum 1, 2: ", sum(myArraySlice...))
}