package main

import "fmt"

const x = "foo"
const y = "bar"

func main() {
    var myMapList = map[string] int {
        x: 10,
        y: 20,
    }

    aX, eX := myMapList[x]
    aY, eY := myMapList[y]
    aFoo, eFoo := myMapList["foo"]
    aBar, eBar := myMapList["bar"]

    fmt.Printf("Value of x: %v %v\n", aX, eX)
    fmt.Printf("Value of y: %v %v\n", aY, eY)
    fmt.Printf("Value of foo: %v %v\n", aFoo, eFoo)
    fmt.Printf("Value of bar: %v %v\n", aBar, eBar)
}
