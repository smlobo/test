//go:build ignore
package main

import (
    "fmt"
    "container/list"
)

func printList(l *list.List) {
	for e := l.Front(); e != nil; e = e.Next() {
		fmt.Printf("%v, ", e.Value)
	}
	fmt.Println()
}

func main() {
    iList := list.New()
    sList := list.New()

    iList.PushBack(10)
    sList.PushBack("Red")
    iList.PushBack(11)
    sList.PushBack("Blue")
    iList.PushBack(12)
    sList.PushBack("Green")

    for eS, eI := sList.Front(), iList.Front();
        eS != nil && eI != nil; 
        eS, eI = eS.Next(), eI.Next() {
        s := eS.Value.(string)
        i := eI.Value.(int)
        fmt.Printf(" %s %d\n", s, i)
    }

    // Insert at front
    e0 := iList.PushFront(8)
    iList.InsertAfter(9, e0)
    en := iList.PushBack(14)
    iList.InsertBefore(13, en)
    fmt.Printf("iList after PushFront/InsertAfter/PushBack/InsertBefore: ")
    printList(iList)

    // concatenate both lists
    iList.PushFrontList(sList)
    fmt.Printf("iList after PushFront of sList: ")
    printList(iList)
}
