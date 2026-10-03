package something

import "testing/quick"

func foo() error {
	return quick.Check(nil, nil)
}
