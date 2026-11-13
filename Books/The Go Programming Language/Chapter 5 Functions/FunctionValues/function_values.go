package main

import (
	"fmt"
	"strings"
)

// Exercise 5.9
// Write a function `expand(s string, f func(string) string) string` that
// replaces each substring ‘‘$foo’’ within s by the text returned by f("foo").
func expand(s string, f func(string) string) string {
	return strings.ReplaceAll(s, "$foo", f("foo"))
}

func bb(s string) string {
	return "b" + s + "b"
}

func main() {
	s1 := "abc$foodef$fooghi$foojk"
	fmt.Printf("%s -> %s\n", s1, expand(s1, bb))
}
