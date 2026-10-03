package main

import (
	"fmt"
)

func lucas(lucasNumbers chan int, quit chan bool) {
	x, y := 2, 1
	for {
		select {
		case lucasNumbers <- x:
			x, y = y, x+y
		case <- quit:
			fmt.Println("Done with lucas numbers!")
			return
		}
	}
}

func main() {
	// Channel for receiving lucas numbers
	nextLucas := make(chan int)
	// Channel that tells the lucas go routine to stop
	stopChannel := make(chan bool)

	go func() {
		for i := 0; i < 10; i++ {
			fmt.Printf("[%d] %d\n", i, <- nextLucas)
		}
		stopChannel <- true
	}()
	lucas(nextLucas, stopChannel)
}