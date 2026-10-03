package main

import (
	"fmt"
	"math"
)

func Sqrt(x float64) float64 {
	z := 1.0
	for math.Abs(z*z-x) > 0.01 {
		z -= (z*z - x) / (2*z)
		fmt.Printf("  try: %.4f\n", z)
	}
	return z
}

func main() {
	for i := 1; i <= 10; i++ {
		fmt.Println("Sqrt:", i)
		fmt.Println(Sqrt(float64(i)))
	}
}
