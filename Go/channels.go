package main

import (
	"fmt"
    "time"
)

// channels send messages from one concurrent "go" function to another
func main() {

    messages := make(chan string)

    go func() { 
        fmt.Println("Waiting 1 sec ...")
        time.Sleep(time.Second)
    	messages <- "ping" 
    }()

    msg := <-messages
    fmt.Println(msg)
}
