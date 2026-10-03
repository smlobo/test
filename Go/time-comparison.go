package main

import (
	"fmt"
	"time"
)

func main() {
	currentDateTime := time.Now().Local()

	fmt.Println(currentDateTime)
	fmt.Println(currentDateTime.Format("2006-01-02_15.04.05.000-0700"))

	if time.Now().After(currentDateTime) {
		fmt.Println("Its after:", currentDateTime)
	} else {
		fmt.Println("Unexpected!!")
	}

	cacheTime := currentDateTime
	if currentDateTime.After(cacheTime) {
		fmt.Println("current after cache")
	} else if currentDateTime.Before(cacheTime) {
		fmt.Println("current before cache")
	} else if currentDateTime.Equal(cacheTime) {
		fmt.Println("current equal cache")
	}

	zeroTime := time.Time{}
	fmt.Printf("Zero time: %s\n", zeroTime)
	if currentDateTime.Before(zeroTime) {
		fmt.Printf("  %s before zeroTime\n", currentDateTime)
	} else {
		fmt.Printf("  %s after zeroTime\n", currentDateTime)
	}
}
