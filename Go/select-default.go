package main

import (
	"fmt"
	"time"
	"math"
)

func checkMersennePrime(n uint64) bool {
	// Base case
	if n < 2 {
		return false
	}

	// Iterate until the square root checking divisibility
	for i := uint64(2); i <= uint64(math.Sqrt(float64(n))); i++ {
		if n%i == 0 {
			return false;
		}
	}

	return true
}

func mersennePrime(theChannel chan uint64) {
	var power uint64 = 1
	for {
		// Is this power a Mersenne Prime?
		if checkMersennePrime(power - 1) {
			theChannel <- (power - 1)
		}
		power *= 2
	}
}

func main() {
	mersennePrimeChannel := make(chan uint64)
	go mersennePrime(mersennePrimeChannel)

	for counter := 0; counter < 9; {
		select {
		case nextMersennePrime := <- mersennePrimeChannel:
			fmt.Printf("\n[%d] %d\n", counter, nextMersennePrime)
			counter++
		default:
			fmt.Print(".")
			time.Sleep(50 * time.Millisecond)
		}
	}
	fmt.Println()
}
