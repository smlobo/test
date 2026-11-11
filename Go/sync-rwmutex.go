//go:build ignore

package main

import (
	"fmt"
	"sync"
	"time"
)

type Settings struct {
    mu     sync.RWMutex
    values map[string]string
}

func NewSettings() *Settings {
	return new(Settings{
		values: map[string]string{},
	})
}

func (s *Settings) Get(key string) (string, bool) {
	s.mu.RLock()
	defer s.mu.RUnlock()
 	v, found := s.values[key]
 	return v, found
}

func (s *Settings) Set(key, value string) {
	s.mu.Lock()
	defer s.mu.Unlock()
	s.values[key] = value
}

func main() {
	s := NewSettings()
	wg := sync.WaitGroup{}

	wg.Add(1)
	go func() {
		defer wg.Done()
		s.Set("foo", "fooX")
	}()
	wg.Add(1)
	go func() {
		defer wg.Done()
		s.Set("bar", "barX")
	}()

	for i := 0; i < 5; i++ {
		wg.Add(1)
		go func(x int) {
			defer wg.Done()
			if v, ok := s.Get("foo"); ok {
				fmt.Printf("[%d] foo = %s\n", x, v)
			} else {
				fmt.Printf("[%d] foo = <not-found>\n", x)
			}
			if v, ok := s.Get("bar"); ok {
				fmt.Printf("[%d] bar = %s\n", x, v)
			} else {
				fmt.Printf("[%d] bar = <not-found>\n", x)
			}
		}(i)
	}

	// pause
	time.Sleep(120 * time.Microsecond)

	// change a key value
	wg.Add(1)
	go func() {
		defer wg.Done()
		s.Set("bar", "barY")
	}()

	wg.Wait()

}