package main

import (
	"fmt"
)

func main() {
	s1 := "s1"
	s2 := "s2"
	var s3 string
	concats1s2 := s1 + s2
	fmt.Println(s1, "+", s2, "=", concats1s2)
	if s3 == "" {
		fmt.Println("s3 is empty")
	}
}