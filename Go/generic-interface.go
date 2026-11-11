//go:build ignore

package main

import "fmt"

type Store[K comparable, V any] interface {
    Save(key K, value V)
    Get(key K) (V, bool)
}

type MapStore[K comparable, V any] struct {
	m map[K]V
}

func NewMapStore[K comparable, V any]() MapStore[K,V] {
	return MapStore[K, V]{m: map[K]V{}}
}

func (m MapStore[K, V]) Save(key K, value V) {
	m.m[key] = value
}

func (m MapStore[K, V]) Get(key K) (V, bool) {
	value, found := m.m[key]
	return value, found
}

func main() {
	siMap := NewMapStore[string, int]()
	siMap.Save("apples", 3)
	v, f := siMap.Get("apples")
	fmt.Printf("apples: %d, %v\n", v, f)
	v, f = siMap.Get("bananas")
	fmt.Printf("bananas: %d, %v\n", v, f)

	fsMap := NewMapStore[float32, string]()
	fsMap.Save(3.14, "pi")
	vs, f := fsMap.Get(3.14)
	fmt.Printf("3.14: %s, %v\n", vs, f)
	vs, f = fsMap.Get(3.15)
	fmt.Printf("3.15: %s, %v\n", vs, f)

}