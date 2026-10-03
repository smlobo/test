package main

import (
	"fmt"
	"math/rand"
	"time"
)

// Cannot return a value from a Go routine

func main() {
	// Seed
	rand.Seed(time.Now().UnixNano())

    randomNumberChannel := make(chan int)
    go randomNumberGenerator(randomNumberChannel)
    //time.Sleep(time.Second)
    randomNumber := <- randomNumberChannel
    fmt.Println("Generated random number:", randomNumber)
}

func randomNumberGenerator(theChannel chan int) {
	// wait for a random milliseconds
	randomInt := rand.Intn(1000)
	fmt.Println("Sleeping for:", randomInt, "ms")
	time.Sleep(time.Duration(randomInt) * time.Millisecond)
	theChannel <- randomInt
}
