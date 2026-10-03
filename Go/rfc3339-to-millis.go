package main

import (
    "fmt"
    "os"
    "time"
)

func main() {
    var inputTime time.Time
    var err error

    if len(os.Args) != 2 {
        inputTime = time.Now()
    } else {
        // Read RFC3339 time
        inputTime, err = time.Parse(time.RFC3339Nano, os.Args[1])
        if err != nil {
            fmt.Printf("failed to parse RFC3339 time: %s\n", os.Args[1])
            os.Exit(-1)
        }
    }

    fmt.Println(inputTime.UnixMilli())
}
