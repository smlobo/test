package main

import (
    "fmt"
    "time"
)

func worker(done chan bool) {
    fmt.Print("worker() working...")
    time.Sleep(time.Second)
    fmt.Println("worker() done")

    done <- true
}

func main() {

    done := make(chan bool, 1)
    go worker(done)

    fmt.Println("main() blocked")
    <-done
    fmt.Println("main() unblocked")
}
