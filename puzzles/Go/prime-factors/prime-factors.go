package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	// Program expects an int parameter
	if len(os.Args) != 2 {
		fmt.Printf("usage: %s <integer>\n", os.Args[0])
		os.Exit(1)
	}

	n, err := strconv.Atoi(os.Args[1])
	if err != nil {
		fmt.Printf("Invalid number: %s\n", os.Args[1])
		os.Exit(1)
	}
	if n < 2 {
		fmt.Printf("Integer >= 2 expected: %d\n", n)
		os.Exit(1)
	}

	fmt.Printf("Prime Factors of ... %d\n", n)

	remainder := n
	count := 2
	prime := true
	for count <= remainder && count != n {
		// Divisible
		if remainder % count == 0 {
			remainder /= count
			fmt.Print(count, ", ")
			prime = false
		} else {
			count++
		}
	}
	if prime {
		fmt.Print("Prime")
	}
	fmt.Println()
}
