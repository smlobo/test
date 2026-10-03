package main

import (
	// "fmt"
	"maps"
	"slices"
)
type GenericSetAlias[T comparable] = map[T]bool

func main() {
	stringSet := GenericSetAlias[string] {"A":true, "B":true, "C":true}
	// fmt.Printf("String set = %v\n", stringSet)
	// print(slices.Collect(maps.Keys(stringSet)))
	print(slices.Collect(maps.Keys(stringSet))[0])
	// fmt.Println(slices.Collect(maps.Keys(stringSet)))

	intSet := GenericSetAlias[int] {1:true, 2:true, 3:true}
	// fmt.Printf("Int set = %v\n", intSet)
	// print(intSet)
	// print(slices.Collect(maps.Keys(intSet)))
	print(slices.Collect(maps.Keys(intSet))[0])
	// fmt.Println(slices.Collect(maps.Keys(intSet)))
}
