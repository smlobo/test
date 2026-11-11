//go:build ignore

package main

import (
	"fmt"
	"sync"
)

type VisitCounter struct {
    counts sync.Map // map[string]int
}

func (v *VisitCounter) Increment(url string) {
	// loop to retry
	for {
		// existing value
		existing, ok := v.counts.LoadOrStore(url, 1)

		// not present, inserted 1, return
		if !ok {
			return
		}

		// increment existing
		existingInt := existing.(int)
		if v.counts.CompareAndSwap(url, existingInt, existingInt+1) {
			return
		}

		// another goroutine incremented, retry
	}
}

func (v *VisitCounter) Count(url string) int {
    count, ok := v.counts.Load(url)
    if !ok {
    	return 0
    }
    return count.(int)
}

func main() {
	v := VisitCounter{}
	wg := sync.WaitGroup{}

	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			for j := 0; j < 1000; j++ {
				v.Increment("foo")
				v.Increment("bar")
			}
		}()
	}
	wg.Wait()

	fmt.Printf("foo count (expected 100000): %d\n", v.Count("foo"))
	fmt.Printf("bar count (expected 100000): %d\n", v.Count("bar"))
	fmt.Printf("moo count (expected 0): %d\n", v.Count("moo"))
}