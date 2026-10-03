package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	rand.Seed(time.Now().UnixNano())

	c := boring()
	
	for i := 0; i < 5; i++ {
		fmt.Printf("[%d] Received: %s\n", i, <-c)
	}
	
	fmt.Println("Done")
}

func boring() <- chan string {
	c := make(chan string)

	go func() {
		for i := 0; ; i++ {
			r := rand.Intn(1000)
			c <- fmt.Sprintf("<%d> %d", i, r)
			time.Sleep(time.Duration(r) * time.Millisecond)
		}
	}()

	return c
}
