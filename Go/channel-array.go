package main

import (
	"fmt"
	"math/rand"
	"time"
	"sync/atomic"
)

func main() {
	// Seed
	rand.Seed(time.Now().UnixNano())

	// atomic counter
	var count uint64;

	// create an array of channels
	var messageArray [5]chan string
	for i := 0; i < 5; i++ {
		messageArray[i] = make(chan string)
	}

	// send 5 messages
	for i := 0; i < 5; i++ {
		go sendMessage(messageArray[i], &count)
	}

	// receive 5 messages
	for i := 0; i < 5; i++ {
		receivedMessage := <- messageArray[i]
		fmt.Printf("[%d] %s\n", i, receivedMessage)
	}
}

func sendMessage(theChannel chan string, countPtr *uint64) {
	// wait for a random milliseconds
	randomInt := rand.Intn(100)
	time.Sleep(time.Duration(randomInt) * time.Millisecond)

	// get the counter value and increment
	myCount := atomic.AddUint64(countPtr, 1)


	theChannel <- fmt.Sprintf("ping with %d - %d", randomInt, myCount)
}
