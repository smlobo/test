package something

import "math"

func foo(f float64) bool {
	return math.IsNaN(f)
}
