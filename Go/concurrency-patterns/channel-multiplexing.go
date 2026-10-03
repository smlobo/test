package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	rand.Seed(time.Now().UnixNano())

	c := fanIn(boring("~X~"), boring(":Y:"))
	
	for i := 0; i < 10; i++ {
		fmt.Printf("[%d] Received: %s\n", i, <-c)
	}
	
	fmt.Println("Done")
}

func fanIn(a, b <- chan string) <- chan string {
	c := make(chan string)
	go func() {
		for {
			c <- <-a
		}
	}()
	go func() {
		for {
			c <- <-b
		}
	}()
	return c
}

func boring(name string) <- chan string {
	c := make(chan string)

	go func() {
		for i := 0; ; i++ {
			r := rand.Intn(1000)
			c <- fmt.Sprintf("%s <%d> %d", name, i, r)
			time.Sleep(time.Duration(r) * time.Millisecond)
		}
	}()

	return c
}
