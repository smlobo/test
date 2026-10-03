package main

import (
	"fmt"
	"os"
	"strconv"
)

func f(from, to chan int) {
	to <- 1 + <-from
}

func main() {
	// number of go routines communicating using the 2 channels
	n, _ := strconv.Atoi(os.Args[1])

	last := make(chan int)

	var from, to chan int
	to = last

	// Pass the message (and increment) along
	for i := 0; i < n; i++ {
		from = make(chan int)
		go f(from, to)
		to = from
	}

	// Seed
	go func(c chan int) {
		c <-0
	}(from)

	fmt.Println(<-last)
}
