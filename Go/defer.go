package main

import "fmt"

func main() {
    fmt.Println("hello world")

    // if false {
    if true {
        return
    }

    fmt.Println("still here!")

    defer func() {
        fmt.Println("in defer!")
    }()

    if false {
        return
    }

    fmt.Println("still her 222!")

    if true {
        return
    }

    fmt.Println("cannot be")
}