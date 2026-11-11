//go:build ignore

package main

import "fmt"

const (
	Read int = 1 << iota
	Write int = 1 << iota
	Execute int = 1 << iota
)

func HasPermission(got, want int) bool {
	and := got & want
	return and == want
}

func main() {
	g, w := Read, Read
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))

	g, w = Read|Write, Read
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))

	g, w = Read, Read|Write
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))

	g, w = Read|Execute, Read|Write
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))

	g, w = Write|Execute, Write
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))

	g, w = Write|Execute, Execute|Write
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))

	g, w = Read|Write|Execute, Execute
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))

	g, w = Read|Write, Write|Execute
	fmt.Printf("%d, %d = %v\n", g, w, HasPermission(g, w))
}
