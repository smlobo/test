package main

import (
	"fmt"
	"golang.org/x/tour/tree"
)

// Walk walks the tree t sending all values
// from the tree to the channel ch.
func Walk(t *tree.Tree, ch chan int) {
	// Nil tree
	if t == nil {
		return
	}

	// Left subtree
	Walk(t.Left, ch)

	// Write to the channel
	ch <- t.Value

	// Right subtree
	Walk(t.Right, ch)
}

// Same determines whether the trees
// t1 and t2 contain the same values.
func Same(t1, t2 *tree.Tree) bool {
	ch1 := make(chan int)
	ch2 := make(chan int)

	go Walk(t1, ch1)
	go Walk(t2, ch2)

	// Compare channel results
	for i := 0; i < 10; i++ {
		r1 := <- ch1
		r2 := <- ch2

		if r1 != r2 {
			return false
		}
	}

	return true
}

func main() {
	walkerChannel := make(chan int)

	// Tree 1
	go Walk(tree.New(1), walkerChannel)
	printWalker("1", walkerChannel)

	// Tree 2
	go Walk(tree.New(2), walkerChannel)
	printWalker("2", walkerChannel)

	// Test is Same
	resultSame := Same(tree.New(1), tree.New(1))
	fmt.Println("Tree 1 == 1 ?", resultSame)

	// Test is not Same
	resultNotSame := Same(tree.New(1), tree.New(2))
	fmt.Println("Tree 1 == 2 ?", resultNotSame)
}

func printWalker(treeName string, ch chan int) {
	// Receive and print the walker results
	fmt.Print("Walker results for", treeName, ": ")
	for i := 0; i < 10; i++ {
		fmt.Print(<- ch, ", ")
	}
	fmt.Println()
}
