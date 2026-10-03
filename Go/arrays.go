package main

import "fmt"

func main() {

    var a [5]int
    fmt.Println("empty:", a)

    a[4] = 100
    fmt.Println("set:", a)
    fmt.Println("get:", a[4])

    fmt.Println("len:", len(a))

    b := [...]int{1, 2, 3, 4, 5}
    fmt.Println("declared:", b)

    var twoD [2][3]int
    for i := 0; i < len(twoD); i++ {
        for j := 0; j < len(twoD[i]); j++ {
            twoD[i][j] = i + j
        }
    }
    fmt.Println("2d: ", twoD)

    // Explicit indices
    type Currency int
    const (
        USD Currency = iota + 2
        EUR
        GBP
        INR
    )
    symbol := [...]string{USD: "$", INR: "₹", GBP: "£", EUR: "€"}
    for index, value := range symbol {
        fmt.Printf("  [%d] %s\n", index, value)
    }
}
