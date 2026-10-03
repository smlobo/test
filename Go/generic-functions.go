package main

import (
	"fmt"
)

func IndexCount[T comparable](s []T, t T) (int, int) {
	index := -1
	count := 0

	for i, v := range s {
		if v == t {
			index = i
			count++
		}
	}

	return index, count
}

func main() {
	// Sort a slice of ints
	si := []int {10, 15, -5, 25, 5, 15}
	i, c := IndexCount(si, 15)
	fmt.Printf("%v, 15 -> %d, %d\n", si, i, c)

	// Sort a slice of strings
	ss := []string {"foo", "bar", "zoo", "moo"}
	i, c = IndexCount(ss, "goo")
	fmt.Printf("%v, goo -> %d, %d\n", ss, i, c)
}
