package main

import "fmt"

func main() {

    m := make(map[string]int)
    fmt.Println("initial len:", len(m))

    m["k1"] = 7
    m["k2"] = 13
    m["k3"] = 12
    m["k4"] = 1
    m["k5"] = 10

    fmt.Println("map:", m)

    v1 := m["k1"]
    fmt.Println("v1: ", v1)

    fmt.Println("len:", len(m))

    delete(m, "k2")
    fmt.Println("delete k2:", m)

    // Optional 2nd value "prs" (present)
    // "_" is a blank identifier since we don't want to use the value for this key
    _, prs := m["k2"]
    fmt.Println("k2 is present?:", prs)

    // Delete all even values
    for key, value := range m {
        fmt.Printf("  [%s] %d\n", key, value)
        if value % 2 == 0 {
            fmt.Printf("    Deleting [%s] %d\n", key, value)
            delete(m, key)
        }
    }
    fmt.Println("After deleting even values:", m)

    // New map n
    n := map[string]int{"foo": 1, "bar": 2}
    fmt.Println("map:", n)


    // New map n2
    n2 := map[string]string{"foo": "fff", "bar": "bbb"}
    fmt.Println("map:", n2)
}
