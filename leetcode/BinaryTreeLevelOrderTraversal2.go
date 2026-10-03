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

func getMaxDepth(r *TreeNode, currentDepth int) int {
	if r == nil {
		return currentDepth
	}
	lMaxDepth := getMaxDepth(r.Left, currentDepth+1)
	rMaxDepth := getMaxDepth(r.Right, currentDepth+1)
	return max(currentDepth+1, lMaxDepth, rMaxDepth)
}

func listAtLevel(r *TreeNode, curr int, level int, list *[]int) {
	if r == nil {
		return
	}
	if curr == level {
		*list = append(*list, r.Val)
		return
	}
	listAtLevel(r.Left, curr+1, level, list)
	listAtLevel(r.Right, curr+1, level, list)
}

func levelOrderBottom(root *TreeNode) [][]int {
	d := getMaxDepth(root, 0)
	fmt.Printf("max depth: %d\n", d)

	l := [][]int {}
	for i := d; i > 0; i-- {
		ithList := []int {}
		listAtLevel(root, 1, i, &ithList)
		l = append(l, ithList)
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
	printTree(r.Left)
	fmt.Printf("%d,", r.Val)
	printTree(r.Right)
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
