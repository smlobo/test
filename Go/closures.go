package main

import "fmt"

func intSeq() func() int {
    i := 0
    return func() int {
        i++
        return i
    }
}

func noIntSeq() func() int {
    i := 0
    return func() int {
        a := i + 1
        return a
    }
}

func main() {

    nextInt := intSeq()
    sameInt := noIntSeq()

    fmt.Println(nextInt(), sameInt())
    fmt.Println(nextInt(), sameInt())
    fmt.Println(nextInt(), sameInt())
    fmt.Printf("The type of 'nextInt' is: %T\n", nextInt)

    newInt2 := intSeq()
    fmt.Println(newInt2())
    fmt.Println(newInt2())
}
