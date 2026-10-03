package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	rand.Seed(time.Now().UnixNano())

	c := make(chan string)
	go boring(c)
	
	for i := 0; i < 5; i++ {
		fmt.Printf("[%d] Received: %s\n", i, <- c)
	}
	
	fmt.Println("Done")
}

func boring(c chan string) {
	for i := 0; true; i++ {
		r := rand.Intn(1000)
		c <- fmt.Sprintf("<%d> %d", i, r)
		time.Sleep(time.Duration(r) * time.Millisecond)
	}
}
