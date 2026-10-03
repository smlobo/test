package main

import (
	"encoding/json"
	"fmt"
	"github.com/buger/jsonparser"
	"math"
	"reflect"
)

type geometry interface {
	area() float64
	perim() float64
}

type rect struct {
	Width, Height float64
}
type circle struct {
	Radius float64
}

func (r rect) area() float64 {
	return r.Width * r.Height
}
func (r rect) perim() float64 {
	return 2*r.Width + 2*r.Height
}

func (c circle) area() float64 {
	return math.Pi * c.Radius * c.Radius
}
func (c circle) perim() float64 {
	return 2 * math.Pi * c.Radius
}

type holder struct {
	G geometry
}

func (h holder) String() string {
	return fmt.Sprintf("<h:%s>", h.G)
}

func measure(g geometry) {
	fmt.Printf("%T: ", g)
	fmt.Println(g)
	fmt.Println(g.area())
	fmt.Println(g.perim())
}

func main() {
	r := rect{Width: 3, Height: 4}
	c := circle{Radius: 7}

	measure(r)
	measure(c)

	h := holder{G: r}
	mm, _ := json.Marshal(h)
	fmt.Printf("Holder h: %s\n", h)
	fmt.Printf("Marshaled holder: %s\n", string(mm))

	unH := holder{}
	_ = json.Unmarshal(mm, &unH)
	fmt.Printf("Unmarshal holder: %s\n", unH)

	gJSON, typ, _, _ := jsonparser.Get(mm, "G")
	if typ != jsonparser.Object {
		fmt.Printf("found 'G' field, but it's not an object")
	}

	rectType := rect{}
	g := reflect.New(reflect.TypeOf(rectType)).Interface()
	fmt.Printf("g type: %T\n", g)
	if err := json.Unmarshal(gJSON, &g); err != nil {
		fmt.Printf("error unmarshal geometry or type `rect`")
	}
	unH.G = g.(geometry)
	fmt.Printf("New Unmarshal holder: %s\n", unH)

	fmt.Printf("h.G type: %T\n", h.G)
	fmt.Printf("unH.G type: %T\n", unH.G)
}
