package main

import (
	"strings"
	"fmt"
)

func WordCount(s string) map[string]int {
	wc := make(map[string]int)
	wordSlice := strings.Fields(s)
	for _, v := range wordSlice {
		wc[v] += 1
	}
	return wc
}

func main() {
	Test(WordCount)
}

func Test(testFunction func(string) map[string]int) {
	testArray := [...]string {
		"I am learning Go!", 
		"The quick brown fox jumped over the lazy dog.", 
		"I ate a donut. Then I ate another donut.", 
		"A man a plan a canal panama.",
	}

	for _, v := range testArray {
		answer := testFunction(v)
		fmt.Printf("%s -> %v\n", v, answer)
	}
}