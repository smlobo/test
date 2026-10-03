package main

import (
	"fmt"
)

type AI interface {
	A() int
}

type DI interface {
	AI
	A() int // Error: duplicate method A
}

type AS struct{}

func (a AS) A() int {
	return 0
}

type BS struct{}

func (b BS) A() int {
	return 1
}

func foo(ai AI) {
	fmt.Printf("foo: ai.A() = %d\n", ai.A())
}

func main() {
	a1 := AS{}
	fmt.Printf("a1.A() = %d\n", a1.A())
	foo(a1)
	b1 := BS{}
	fmt.Printf("b1.A() = %d\n", b1.A())
	foo(b1)
}
