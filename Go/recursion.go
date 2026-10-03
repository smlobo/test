package main

import "fmt"

func fact(n int) int {
	fmt.Println("factorial: ", n)
    if n == 0 {
        return 1
    }
    return n * fact(n-1)
}

func main() {
    fmt.Println(fact(7))
}
