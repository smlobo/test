package main

import (
    "fmt"
    "iter"
)

// Set holds a set of elements.
type Set[E comparable] struct {
    m map[E]struct{}
}

// New returns a new [Set].
func New[E comparable]() *Set[E] {
    return &Set[E]{m: make(map[E]struct{})}
}

// Add adds an element to a set.
func (s *Set[E]) Add(v E) {
    s.m[v] = struct{}{}
}

// Contains reports whether an element is in a set.
func (s *Set[E]) Contains(v E) bool {
    _, ok := s.m[v]
    return ok
}

// All is an iterator over the elements of s.
func (s *Set[E]) All() iter.Seq[E] {
    return func(yield func(E) bool) {
        for v := range s.m {
            if !yield(v) {
                return
            }
        }
    }
}

func main() {
    runeSet := New[rune]()
    runeSet.Add('क')
    runeSet.Add('ख')
    runeSet.Add('ग')
    for v := range runeSet.All() {
        fmt.Printf("%s, ", string(v))
    }
    fmt.Println()
}
