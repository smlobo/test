package main

import (
    "fmt"
)

func main() {
    shadowX := 10
    notShadowX := 100
    fmt.Printf("shadowX = %d, notShadowX = %d\n", shadowX, notShadowX)
    {
        shadowX = 20
        notShadowX := 200
        fmt.Printf("inside shadowX = %d, notShadowX = %d\n", shadowX, notShadowX)
    }
    fmt.Printf("outside shadowX = %d, notShadowX = %d\n", shadowX, notShadowX)
    {
        shadowX = 30
        notShadowX := 300
        fmt.Printf("inside2 shadowX = %d, notShadowX = %d\n", shadowX, notShadowX)
    }
    fmt.Printf("outside2 shadowX = %d, notShadowX = %d\n", shadowX, notShadowX)

}
