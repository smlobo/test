package main

import "fmt"

type Rectangle struct {
	width, height int
}

func (r *Rectangle) area() int {
	return r.width * r.height
}

func (r Rectangle) perimeter() int {
	return 2*r.width + 2*r.height
}

func main() {
	r := Rectangle{width: 10, height: 5}

	fmt.Printf("with Type: %T\n", r)
	fmt.Println("  area (explicit):\t", (&r).area())
	fmt.Println("  area (implicit):\t", r.area())
	fmt.Println("  perimeter:\t\t", r.perimeter())

	rp := &r
	fmt.Printf("with Type: %T\n", rp)
	fmt.Println("  area:\t\t\t", rp.area())
	fmt.Println("  perimeter (explicit):\t", (*rp).perimeter())
	fmt.Println("  perimeter (implicit):\t", rp.perimeter())
}
