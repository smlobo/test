package main

import "fmt"

func main() {
	// Exercise 4.3
	testArray := [10]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
	fmt.Println("testArray initial:", testArray)
	arrayReverse(&testArray)
	fmt.Println("testArray reversed:", testArray)

	// Exercise 4.4
	testSlice := testArray[2:7]
	fmt.Println("testSlice initial:", testSlice)
	sliceRotate(testSlice, 3)
	fmt.Println("testSlice rotate 3:", testSlice)
}

func arrayReverse(arrayPointer *[10]int) {
	for i, j := 0, len(arrayPointer)-1; i < j; i, j = i+1, j-1 {
		arrayPointer[i], arrayPointer[j] = arrayPointer[j], arrayPointer[i]
	}
}

func sliceRotate(theSlice []int, n int) {
	// Make a copy
	theCopy := make([]int, len(theSlice))
	copy(theCopy, theSlice)

	for i := 0; i < len(theSlice); i++ {
		dst := (i+n)%len(theSlice)
		theSlice[dst] = theCopy[i]
	}
}