package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	rand.Seed(time.Now().UnixNano())

	c := boring()

	timeout := time.After(5 * time.Second)

	for i := 0; ; i++ {
		select {
		case s := <-c:
			fmt.Printf("[%d] boring(): %s\n", i, s)
		case <-timeout:
			fmt.Println("5 sec elapsed")
			return
		}
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
