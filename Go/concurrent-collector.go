//go:build ignore

package main

import (
	"fmt"
	"sync"
)

type Batch struct {
    Values []int
}

type Collector struct {
    batches []*Batch
    batchSize int
    mutex sync.Mutex
}

func NewCollector(batchSize int) *Collector {
	return new(Collector{
		batches: make([]*Batch, 0),
		batchSize: batchSize,
	})
}

func (c *Collector) Add(value int) {
	c.mutex.Lock()
	defer c.mutex.Unlock()

	var batch *Batch
	if len(c.batches) == 0 || 
			len(c.batches[len(c.batches)-1].Values) == c.batchSize {
		batch = new(Batch{
			Values: make([]int, 0),
		})
		c.batches = append(c.batches, batch)
	} else {
		batch = c.batches[len(c.batches)-1]
	}
	batch.Values = append(batch.Values, value)
	// fmt.Printf("%d -> %v\n", value, batch.Values)
}

func (c *Collector) Snapshot() [][]int {
	c.mutex.Lock()
	defer c.mutex.Unlock()

	retSlice := make([][]int, len(c.batches))

	for index, batch := range c.batches {
		retSlice[index] = make([]int, len(batch.Values))
		copy(retSlice[index], batch.Values)
		// fmt.Printf("[%d] %v\n", index, batch.Values)
	}

	// fmt.Printf("Pre Snapshot: %v\n", retSlice)
	return retSlice
}

func main() {
	c := NewCollector(3)
	var wg sync.WaitGroup

	for _, val := range []int{10, 20, 30, 40} {
		wg.Add(1)
		go func(value int) {
			defer wg.Done()
			c.Add(value)
		}(val)
	}

	wg.Wait()

	fmt.Printf("Snapshot: %v\n", c.Snapshot())
}
