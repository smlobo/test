//go:build ignore

package main

import (
	"fmt"
)

// Definition for a binary tree node.
type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

type LevelNode struct {
	Level int
	Node *TreeNode
}

func levelOrderBottom(root *TreeNode) [][]int {
	l := [][]int {}
	if root == nil {
		return l
	}
	bfsQueue := []LevelNode {LevelNode{
		Level: 0,
		Node: root,
	}}
	index := 0
	for len(bfsQueue) > index {
		lNode := bfsQueue[index]
		if lNode.Node == nil {
			index++
			continue
		}
		// Create the sub list
		if len(l) == lNode.Level {
			levelList := []int{}
			l = append(l, levelList)
		}
		l[lNode.Level] = append(l[lNode.Level], lNode.Node.Val)
		bfsQueue = append(bfsQueue, LevelNode{
			Level: lNode.Level + 1,
			Node: lNode.Node.Left,
		})
		bfsQueue = append(bfsQueue, LevelNode{
			Level: lNode.Level + 1,
			Node: lNode.Node.Right,
		})
		index++
	}
	// Reverse
	for i, j := 0, len(l)-1; i < j; i, j = i+1, j-1 {
		l[i], l[j] = l[j], l[i]
	}
	return l
}

func buildTree(l []int, i int) *TreeNode {
	if i >= len(l) {
		return nil
	}
	val := l[i]
	if val < 0 {
		return nil
	}
	left := buildTree(l, 2*(i+1)-1)
	right := buildTree(l, 2*(i+1))
	return &TreeNode{
		Val: val,
		Left: left,
		Right: right,
	}
}

func listToBT(l []int) *TreeNode {
	if len(l) == 0 {
		return nil
	}
	return buildTree(l, 0)
}

func printTree(r *TreeNode) {
	if r == nil {
		fmt.Printf("nil,")
		return
	}
	queue := []*TreeNode {r}
	index := 0
	for len(queue) > index {
		if queue[index] != nil {
			fmt.Printf("%d,", queue[index].Val)
			queue = append(queue, queue[index].Left)
			queue = append(queue, queue[index].Right)
		}
		index++
	}
}

func printLevelOrder(a [][]int) {
	fmt.Printf("[")
	for i := 0; i < len(a); i++ {
		fmt.Printf("[")
		for j := 0; j < len(a[i]); j++ {
			fmt.Printf("%d,", a[i][j])
		}
		fmt.Printf("]")
	}
	fmt.Println("]")
}

func main() {
	t1 := listToBT([]int{3,9,20,-1,-1,15,7})
	printTree(t1)
	fmt.Println()
	a1 := levelOrderBottom(t1)
	printLevelOrder(a1)

	t2 := listToBT([]int{1})
	printTree(t2)
	fmt.Println()
	a2 := levelOrderBottom(t2)
	printLevelOrder(a2)

	t3 := listToBT([]int{})
	printTree(t3)
	fmt.Println()
	a3 := levelOrderBottom(t3)
	printLevelOrder(a3)
}
