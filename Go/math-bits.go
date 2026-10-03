package something

import "math/bits"

func foo(x uint) int {
	return bits.Len(x)
}
