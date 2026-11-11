//go:build ignore

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

func Unique[T comparable](items []T) []T {
	tSet := map[T]struct{}{}
	uniqueItems := []T{}
	for _, item := range items {
		if _, found := tSet[item]; found {
			continue
		}
		tSet[item] = struct{}{}
		uniqueItems = append(uniqueItems, item)
	}
	return uniqueItems
}

func main() {
	// Index & count of a slice of ints
	si := []int {10, 15, -5, 25, 5, 15}
	i, c := IndexCount(si, 15)
	fmt.Printf("%v, 15 -> %d, %d\n", si, i, c)

	// Unique elements
	fmt.Printf("%v unique: %v\n", si, Unique[int](si))

	// Index & count of a slice of strings
	ss := []string {"foo", "bar", "zoo", "moo", "bar"}
	i, c = IndexCount(ss, "goo")
	fmt.Printf("%v, goo -> %d, %d\n", ss, i, c)

	// Unique elements
	fmt.Printf("%v unique: %v\n", ss, Unique[string](ss))
}
