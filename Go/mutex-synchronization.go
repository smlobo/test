package main

import (
    "fmt"
    "time"
    "sync"
)

func worker(mu *sync.Mutex) {
    mu.Lock()

    fmt.Print("worker() working...")
    time.Sleep(time.Second)
    fmt.Println("worker() done")

    mu.Unlock()
}

func main() {

    var syncMutex sync.Mutex 
    go worker(&syncMutex)

    fmt.Println("main() blocked (waiting for mutex Lock)")
    syncMutex.Lock()
    defer syncMutex.Unlock()
    fmt.Println("main() unblocked (acquired mutex lock)")
}
