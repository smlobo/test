package main

import (
	"fmt"
	"image/color"
	"math"
)

type Point struct {
	x, y float64
}

func (p *Point) distance() float64 {
	return math.Sqrt(p.x*p.x + p.y*p.y)
}

type ColoredPoint struct {
	Point
	*color.RGBA
}
func main() {
	p := Point{3, 4}
	fmt.Printf("Point p: %v - distance: %.3f\n", p, p.distance())

	cp:= ColoredPoint{Point{6, 8}, &color.RGBA{255, 0, 0, 255}}
	fmt.Printf("Colored point: %v - distance: %.3f\n", cp, cp.distance())
	fmt.Printf("  color: %v : %v\n", cp.RGBA, *cp.RGBA)
}
