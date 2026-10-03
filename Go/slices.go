package main

import "fmt"

func main() {

    s := make([]string, 3)
    fmt.Println("emp:", s)
    fmt.Println("len:", len(s), "cap:", cap(s))

    s[0] = "a"
    s[1] = "b"
    s[2] = "c"
    fmt.Println("set:", s)
    fmt.Printf("set: %v\n", s)
    fmt.Println("get:", s[2])

    fmt.Println("len:", len(s), "cap:", cap(s))

    s = append(s, "d")
    s = append(s, "e", "f")
    fmt.Println("append:", s)
    fmt.Println("len:", len(s), "cap:", cap(s))

    s = append(s, s...)
    fmt.Println("append ellipsis:", s)
    fmt.Println("len:", len(s), "cap:", cap(s))

    for index, value := range s {
       fmt.Printf("  [%d] %s\n", index, value)
    }

    c := make([]string, len(s))
    copy(c, s)
    fmt.Println("copy:", c)
    fmt.Println("len:", len(s), "cap:", cap(s))

    l := s[2:5]
    fmt.Println("slice 2-5 :", l)
    fmt.Println("len:", len(l), "cap:", cap(l))

    l = s[:5]
    fmt.Println("slice -5 :", l)
    fmt.Println("len:", len(l), "cap:", cap(l))

    l = s[2:]
    fmt.Println("slice 2- :", l)
    fmt.Println("len:", len(l), "cap:", cap(l))

    // slice without make - if a size is specified ([3]), it becomes an array
    t := []string{"g", "h", "i"}
    fmt.Println("dcl:", t)

    // nil slice
    var u []string
    fmt.Printf("u: %+v, len=%d, cap=%d\n", u, len(u), cap(u))

    twoD := make([][]int, 3)
    //for i := 0; i < 3; i++ {
    for i := 0; i < len(twoD); i++ {
        innerLen := i + 1
        twoD[i] = make([]int, innerLen)
        //for j := 0; j < innerLen; j++ {
        for j := 0; j < len(twoD[i]); j++ {
            twoD[i][j] = i + j
        }
    }
    fmt.Println("2d: ", twoD)
}
