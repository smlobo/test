package main

import "fmt"

func main() {
    i := 45
    switch {
    case i < 50:
        fmt.Println("i is less than 50")
        if i < 50 {
            fmt.Println("i is still less than 50")
        }
        fmt.Println("i yet again less than 50")
    case i < 10:
        fmt.Println("i is less than 10")
        if i < 10 {
            fmt.Println("i is still less than 10")
        }
        fmt.Println("i yet again less than 10")
    case i < 100:
        fmt.Println("i is less than 100")
        if i < 100 {
            fmt.Println("i is still less than 100")
        }
        fmt.Println("i yts again less than 100")
    }
    fmt.Println("done with switch")
}
