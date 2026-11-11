//go:build ignore

package main

import (
	"fmt"
	"sync"
)

type Counter struct {
	counter int
	mutex sync.Mutex
}

func (c *Counter) Increment() {
    c.mutex.Lock()
    defer c.mutex.Unlock()
    c.counter++
}

func (c *Counter) Value() int {
    c.mutex.Lock()
    defer c.mutex.Unlock()
    return c.counter
}

func main() {
	// counter
	c := Counter{counter: 0}

	// wait group for go routines
	var wg sync.WaitGroup

	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			for j := 0; j < 1000; j++ {
				c.Increment()
			}
		}()
	}

	wg.Wait()

	fmt.Printf("final count: %d\n", c.Value())
}
