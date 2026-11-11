//go:build ignore

package main

import (
	"fmt"
	"sync"
)

type Config struct {
    Value string
}

type Loader struct {
    once   sync.Once
    config *Config
    loads  int // for observing how often the config is loaded
}

func (l *Loader) Get() *Config {
    l.once.Do(func() {
    	l.config = &Config{Value: "loaded"}
    	l.loads++
    })
    return l.config
}

func main() {
	l := Loader{}
	wg := sync.WaitGroup{}

	for i := 0; i < 4; i++ {
		wg.Add(1)
		go func(x int) {
			defer wg.Done()
			config := l.Get()
			fmt.Printf("[%d] {%p} %v\n", x, config, *config)
		}(i)
	}
	wg.Wait()

	fmt.Printf("Loader count: %d\n", l.loads)
}
