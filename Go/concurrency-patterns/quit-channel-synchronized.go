package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	rand.Seed(time.Now().UnixNano())

	quit := make(chan string)
	c := boring(quit)
	
	for i := 0; i < 5; i++ {
		fmt.Printf("[%d] Received: %s\n", i, <-c)
	}

	quit <- "Stop execution from main()"
	
	fmt.Println("Confirmation from boring():", <-quit)
}

func boring(quit chan string) <- chan string {
	c := make(chan string)

	go func() {
		for i := 0; ; i++ {
			r := rand.Intn(1000)

			select {
			case c <- fmt.Sprintf("<%d> %d", i, r):
				// empty
			case qmsg := <-quit:
				fmt.Printf("boring(): received from main() -> %s\n", qmsg)
				quit <- "Aye Aye from boring()"
				return
			}
			time.Sleep(time.Duration(r) * time.Millisecond)
		}
	}()

	return c
}
