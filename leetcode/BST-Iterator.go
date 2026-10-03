package main

import (
	"fmt"
)

/**
 * Definition for a binary tree node.
 */

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type BSTIterator struct {
	nextIndex int
	nodeSlice []*TreeNode
}

func Constructor(root *TreeNode) BSTIterator {
	retIter := BSTIterator{}
	retIter.walkBST(root)
	retIter.nextIndex = 0
	return retIter
}

func (this *BSTIterator) walkBST(node *TreeNode) {
    if node == nil {
        return
    }
	this.walkBST(node.Left)
	this.nodeSlice = append(this.nodeSlice, node)
	this.walkBST(node.Right)
}

func (this *BSTIterator) Next() int {
	if this.nextIndex == len(this.nodeSlice) {
		return 0
	}
	this.nextIndex++
	return this.nodeSlice[this.nextIndex-1].Val
}

func (this *BSTIterator) HasNext() bool {
	return this.nextIndex < len(this.nodeSlice)
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */

func main() {
	// test 1
	n3 := TreeNode{Val: 3}
	n9 := TreeNode{Val: 9}
	n20 := TreeNode{Val: 20}
	n15 := TreeNode{15, &n9, &n20}
	n7 := TreeNode{7, &n3, &n15}
	bstObj := Constructor(&n7)
    for i := 0; i < 5; i++ {
        fmt.Printf("%d, %v\n", bstObj.Next(), bstObj.HasNext())
    }

}
