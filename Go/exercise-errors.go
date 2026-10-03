package main

import (
	"fmt"
	"math"
)

type ErrNegativeSqrt float64

func (e ErrNegativeSqrt) Error() string {
	return fmt.Sprintf("cannot Sqrt negative number: %.4f", e)
}

func Sqrt(x float64) (float64, error) {
	if x < 0 {
		e := ErrNegativeSqrt(x)
		return x, e
	}

	z := 1.0
	for math.Abs(z*z-x) > 0.01 {
		z -= (z*z - x) / (2*z)
		fmt.Printf("  try: %.4f\n", z)
	}
	return z, nil
}

func main() {
	for i := -1; i <= 4; i++ {
		fmt.Println("Sqrt:", i)
		fmt.Println(Sqrt(float64(i)))
	}
}
