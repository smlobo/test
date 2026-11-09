//go:build ignore

package main

import (
    "container/heap"
    "fmt"
    "math/rand"
    "time"
)

type Person struct {
    Name string
    Age int
}

func (p Person) String() string {
    return fmt.Sprintf("{%s,%d}", p.Name, p.Age)
}

type PersonPQ []Person

func (p PersonPQ) Len() int {
    return len(p)
}

func (p PersonPQ) Less(i, j int) bool {
    return p[i].Age < p[j].Age
}

func (p PersonPQ) Swap(i, j int) {
    p[i], p[j] = p[j], p[i]
}

func (p *PersonPQ) Push(x any) {
    *p = append(*p, x.(Person))
}

func (p *PersonPQ) Pop() any {
    x := (*p)[len(*p)-1]
    *p = (*p)[0:len(*p)-1]
    return x
}

func main() {
    // Seed
    rand.Seed(time.Now().UnixNano())

    pq := &PersonPQ{}
    heap.Init(pq)
    heap.Push(pq, Person{"Adam", rand.Intn(100)})
    heap.Push(pq, Person{"Ben", rand.Intn(100)})
    heap.Push(pq, Person{"Caroline", rand.Intn(100)})

    for pq.Len() > 0 {
        p := heap.Pop(pq)
        fmt.Printf("%v\n", p)
    }
}
