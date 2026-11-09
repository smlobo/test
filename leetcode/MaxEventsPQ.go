//go:build ignore

package main

import (
	"container/heap"
	"fmt"
	"slices"
)

// []int PQ
type EventPQ [][]int

func (p EventPQ) Len() int {
    return len(p)
}

func (p EventPQ) Less(i, j int) bool {
    return p[i][1] < p[j][1]
}

func (p EventPQ) Swap(i, j int) {
    p[i], p[j] = p[j], p[i]
}

func (p *EventPQ) Push(x any) {
    *p = append(*p, x.([]int))
}

func (p *EventPQ) Pop() any {
    x := (*p)[len(*p)-1]
    *p = (*p)[0:len(*p)-1]
    return x
}

func maxEvents(events [][]int) int {
    // find min and max times
    minDay := 10^5
    maxDay := 1
    for _, event := range events {
        if event[0] < minDay {
            minDay = event[0]
        }
        if event[1] > maxDay {
            maxDay = event[1]
        }
    }

    // sort by start time
    slices.SortFunc(events, func(a, b []int) int {
        return a[0] - b[0]
    })

    eventsAttended := 0
    eventIndex := 0
    eventPQ := &EventPQ{}
    heap.Init(eventPQ)

    // iterate over possible days
    for day := minDay; day <= maxDay; day++ {
        // add events starting on 'day' to the PQ
        for ; eventIndex < len(events); eventIndex++ {
            // end condition
            if events[eventIndex][0] > day {
                break
            }
            heap.Push(eventPQ, events[eventIndex])
        }

        // pop the earliest ending event
        if eventPQ.Len() > 0 {
            heap.Pop(eventPQ)
            eventsAttended++
        }

        // remove all events that end on 'day'
        for eventPQ.Len() > 0 {
            nextEvent := heap.Pop(eventPQ).([]int)
            if nextEvent[1] > day {
                heap.Push(eventPQ, nextEvent)
                break
            }
        }
    }

    return eventsAttended
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