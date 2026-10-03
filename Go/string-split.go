package main

import (
	"fmt"
	"strings"
)

func main() {

	s1 := "abcdebcfg"
	s2 := "bc"
	sR := strings.Split(s1, s2)
	fmt.Printf("%s | %s = ", s1, s2)
	for i := 0; i < len(sR); i++ {
		fmt.Printf("%s{%d}, ", sR[i], len(sR[i]))
	}
	fmt.Println()

	s1 = "abcdabcfgab"
	s2 = "ab"
	sR = strings.Split(s1, s2)
	fmt.Printf("%s | %s = ", s1, s2)
	for i := 0; i < len(sR); i++ {
		fmt.Printf("%s{%d}, ", sR[i], len(sR[i]))
	}
	fmt.Println()
}