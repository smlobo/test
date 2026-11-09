//go:build ignore

package main

import (
    "fmt"
    "slices"
)

func maxEvents(events [][]int) int {
    // sort by duration
    slices.SortFunc(events, func(a, b []int) int {
        aDuration := a[1] - a[0] + 1
        bDuration := b[1] - b[0] + 1
        if aDuration != bDuration {
            return aDuration - bDuration
        }
        return a[0] - b[0]
    })
    // fmt.Printf("Sorted: %v\n", events)

    // set of taken slots
    slotSet := map[int][]int{}

    for _, event := range events {
        // iterate over event slots
        for eSlot := event[0]; eSlot <= event[1]; eSlot++ {
            prevEvent, ok := slotSet[eSlot]
            if !ok {
                slotSet[eSlot] = event
                break
            }
            // can prevEvent be moved?
            for pESlot := prevEvent[0]; pESlot <= prevEvent[1]; pESlot++ {
                _, ok := slotSet[pESlot]
                if !ok {
                    slotSet[pESlot] = prevEvent
                    delete(slotSet, eSlot)
                    break
                }
            }
            // try again
            _, ok = slotSet[eSlot]
            if !ok {
                slotSet[eSlot] = event
                break
            }
        }
        // fmt.Printf("%v; %d, %d\n", event, lastReservedTime, numEvents)
    }

    return len(slotSet)
}

func main() {
    t := [][]int{{1,2},{3,4},{2,3},}
    a := maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{1,2},{3,4},{2,3},{1,2},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{1,1},{2,2},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{3,3},{2,2},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{2,2},{2,2},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{2,3},{2,3},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{1,2},{2,2},{3,3},{3,4},{3,4},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{1,2},{1,2},{3,3},{1,5},{1,5},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)

    t = [][]int{{1,3},{1,3},{1,3},{3,4},}
    a = maxEvents(t)
    fmt.Printf("%v -> %d\n", t, a)
}