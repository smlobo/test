//go:build ignore

package main

import (
	"fmt"
)

func produce(n int) (recvChan <-chan int) {
	defer func() {
		if r := recover(); r != nil {
			fmt.Printf("Recovered from: %v\n", r)
			numChan := make(chan int)
			close(numChan)
			recvChan = numChan
		}
	}()

	numChan := make(chan int, n)
	go func() {
		for i := 1; i <= n; i++ {
			numChan <- i
		}
		close(numChan)
	}()

	recvChan = numChan
	return
}

func main() {
	fmt.Printf("produce(3): ")
	receiveChan := produce(3)
	for value := range receiveChan {
		fmt.Printf("%d, ", value)
	}
	fmt.Println()

	fmt.Printf("produce(0): ")
	receiveChan = produce(0)
	for value := range receiveChan {
		fmt.Printf("%d, ", value)
	}
	fmt.Println()

	fmt.Printf("produce(-1): ")
	receiveChan = produce(-1)
	for value := range receiveChan {
		fmt.Printf("%d, ", value)
	}
	fmt.Println()
}