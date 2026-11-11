//go:build ignore
package main

import (
	"fmt"
)

// List represents a singly-linked list that holds
// values of any type.
type List[T any] struct {
	next *List[T]
	val  T
}

func (l *List[T]) String() string {
	if l == nil {
		return "<nil>"
	}
	return fmt.Sprintf("<%v>->%v", l.val, l.next)
}

func (l *List[T]) Reverse() *List[T] {
	var prev, curr, next *List[T]
	prev, curr, next = nil, l, nil
	for curr != nil {
		next = curr.next
		curr.next = prev
		prev = curr
		curr = next
	}
	return prev
}

func main() {
	// node 1 / head
	n1 := List[int] {nil, 10}
	// node 2
	n2 := List[int] {nil, 3}
	// node 3
	n3 := List[int] {nil, 44}
	// node 4
	n4 := List[int] {nil, 222}

	fmt.Printf("Nodes (before connecting): %v, %v, %v, %v\n", 
		&n1, &n2, &n3, &n4)

	// Connect
	head := &n1
	n1.next = &n2
	n2.next = &n3
	n3.next = &n4

	fmt.Printf("List (connected): %v\n", head)

	fmt.Printf("List (reversed): %v\n", head.Reverse())

	fmt.Printf("Single element list: %v", &n1)
	fmt.Printf("; (reversed): %v\n", (&n1).Reverse())

	fmt.Printf("2 element list: %v", &n2)
	fmt.Printf("; (reversed): %v\n", (&n2).Reverse())
}
