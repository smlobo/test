package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	rand.Seed(time.Now().UnixNano())

	x := boring("~X~")
	y := boring(":Y:")
	
	for i := 0; i < 5; i++ {
		fmt.Printf("[%d] Received: %s\n", i, <- x)
		fmt.Printf("[%d] Received: %s\n", i, <- y)
	}
	
	fmt.Println("Done")
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
