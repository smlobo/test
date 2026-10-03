package main

import (
    "fmt"
    "math"
    "encoding/json"
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

func (h holder) String() (string) {
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
    // c := circle{Radius: 7}

    h := holder{G: r}
    mm, _ := json.Marshal(h)
    fmt.Printf("Holder h: %s\n", h)
    fmt.Printf("Marshaled holder: %s\n", string(mm))

    fmt.Printf("h.G type: %T\n", h.G)
    rInt := reflect.ValueOf(r).Interface()
    h.G = rInt.(geometry)
    fmt.Printf("new h.G type: %T\n", h.G)
}
