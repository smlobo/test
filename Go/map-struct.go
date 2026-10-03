package main

import "fmt"

type Color struct {
	name string
	rgb int
}

type Shape struct {
	name string
	area int
	color *Color
}

func main() {
	shapeMap := make(map[Shape]int)

	s1 := Shape{"square", 10, &Color{"red", 100}}
	s2 := Shape{"rectangle", 20, &Color{"blue", 1}}

	shapeMap[s1] = -5
	shapeMap[s2] = -3
	printMap("orig map", shapeMap)

	s3 := s1
	shapeMap[s3] = -100
	printMap("map with dupl", shapeMap)

	s4 := Shape{"square", 90-80, &Color{"red", 100}}
	shapeMap[s4] = 666
	printMap("map with dupl 2", shapeMap)
}

func printMap(desc string, m map[Shape]int) {
	fmt.Printf("%s:\n", desc)
	for k, v := range m {
		fmt.Printf("  [%s] %d\n", k, v)
	}
}
