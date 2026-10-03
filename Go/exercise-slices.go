package main

import (
	"fmt"
	"os"
	"strconv"
)

func Pic(dx, dy int) [][]uint8 {
	var counter uint8
	a := make([][]uint8, dy)
	fmt.Printf("%v\n", a)
	for i := 0; i < dy; i++ {
		a[i] = make([]uint8, dx)
		for j := 0; j < dx; j++ {
			if counter > 9 {
				counter = 0
			}
			a[i][j] = counter
			counter++
		}
	}
	return a
}

func main() {
	dx, _ := strconv.Atoi(os.Args[1])
	dy, _ := strconv.Atoi(os.Args[2])
	x := Pic(dx, dy)
	printPic(x)
}

func printPic(p [][]uint8) {
	for i, v := range p {
		fmt.Printf("[%d] %v\n", i, v)
	}
}