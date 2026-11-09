//go:build ignore

package main

import (
    "fmt"
)

// Definition for singly-linked list.
type ListNode struct {
    Val int
    Next *ListNode
}

func (l *ListNode) String() string {
    retString := ""
    for l != nil {
        retString = fmt.Sprintf("%s,%d", retString, l.Val)
        l = l.Next
    }
    return retString
}

func removeNthFromEnd(head *ListNode, n int) *ListNode {
    // advance front pointer to 1 over n
    frontPtr := head
    count := 0
    for frontPtr != nil && count <= n {
        frontPtr = frontPtr.Next
        count++
    }

    // iterate together so that when frontPtr hits the end, removeNextPtr 
    // is at node prev to delete
    removeNextPtr := head
    for frontPtr != nil {
        frontPtr = frontPtr.Next
        removeNextPtr = removeNextPtr.Next
    }

    // corner case - remove head
    if count == n {
        head = head.Next
    } else {
        removeNextPtr.Next = removeNextPtr.Next.Next
    }

    return head
}

func main() {
    t := ListNode{
        1,
        &ListNode{
            2,
            &ListNode{
                3,
                &ListNode{
                    4,
                    &ListNode{
                        5,
                        nil,
                    },
                },
            },
        },
    }
    fmt.Printf("%s; 2 -> ", &t)
    a := removeNthFromEnd(&t, 2)
    fmt.Printf("%s\n", a)

    fmt.Printf("%s; 1 -> ", a)
    a = removeNthFromEnd(a, 1)
    fmt.Printf("%s\n", a)

    fmt.Printf("%s; 3 -> ", a)
    a = removeNthFromEnd(a, 3)
    fmt.Printf("%s\n", a)

    fmt.Printf("%s; 1 -> ", a)
    a = removeNthFromEnd(a, 1)
    fmt.Printf("%s\n", a)

    fmt.Printf("%s; 1 -> ", a)
    a = removeNthFromEnd(a, 1)
    fmt.Printf("%s\n", a)

}
