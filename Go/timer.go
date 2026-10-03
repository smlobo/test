package main

import (
	"fmt"
	"time"
)

func main() {
	timer := time.NewTimer(3 * time.Second)

	time.Sleep(1 * time.Second)

	select {
	case <-timer.C:
		fmt.Println("done")
	default:
		fmt.Println("not done yet")
	}

	time.Sleep(3 * time.Second)

	select {
	case <-timer.C:
		fmt.Println("done now")
	default:
		fmt.Println("still not done")
	}
}

