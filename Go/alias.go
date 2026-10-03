package main

import (
    "fmt"
)

type A = string
type B string

func main() {
    var a A = "hello"
    var b B = "hello"
    fmt.Printf("a is %T\nb is %T\n", a, b)
}
