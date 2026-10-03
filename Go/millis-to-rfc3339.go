package main

import (
    "fmt"
    "os"
    "time"
    "strconv"
)

func main() {
    if len(os.Args) != 2 {
        fmt.Printf("Usage: %s <millis-since-epoch>\n", os.Args[0])
        os.Exit(-1)
    }

    // Read millisecond time
    millis, err := strconv.ParseInt(os.Args[1], 10, 64)
    if err != nil {
        fmt.Printf("failed to parse milli time: %s\n", os.Args[1])
        os.Exit(-1)
    }

    inputTime := time.UnixMilli(millis)

    fmt.Printf("%s / %s\n", inputTime.Format(time.RFC3339Nano), 
        inputTime.UTC().Format(time.RFC3339Nano))
}
