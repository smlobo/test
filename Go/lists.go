package main

import (
    "fmt"
    "container/list"
)

func main() {
    iList := list.New()
    sList := list.New()

    iList.PushBack(10)
    sList.PushBack("Red")
    iList.PushBack(11)
    sList.PushBack("BLue")
    iList.PushBack(12)
    sList.PushBack("Green")

    for eS, eI := sList.Front(), iList.Front();
        eS != nil && eI != nil; 
        eS, eI = eS.Next(), eI.Next() {
        s := eS.Value.(string)
        i := eI.Value.(int)
        fmt.Printf(" %s %d\n", s, i)
    }
}