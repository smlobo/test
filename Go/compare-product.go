//go:build ignore

package main

import (
	"fmt"
	"maps"
	"slices"
)

type Product struct {
	Id int
	Tags []string
	Attributes map[string]string
}

func (p Product) Equals(q Product) bool {
	return p.Id == q.Id && slices.Equal(p.Tags, q.Tags) && 
		maps.Equal(p.Attributes, q.Attributes)
}

func main() {
	p1 := Product{
		Id: 10,
		Tags: []string{"a", "b"},
		Attributes: map[string]string{
			"x": "xx",
			"y": "yy",
		},
	}

	p2 := Product{
		Id: 10,
		Tags: []string{"a", "b"},
		Attributes: map[string]string{
			"y": "yy",
			"x": "xx",
		},
	}

	fmt.Printf("%v == %v ? %v\n", p1, p2, p1.Equals(p2))

	p2.Tags[0] = "b"
	p2.Tags[1] = "a"

	fmt.Printf("%v == %v ? %v\n", p1, p2, p1.Equals(p2))
}
