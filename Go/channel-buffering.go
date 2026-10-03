package main

import "fmt"

func main() {

	// buffer channel with upto 2 messages
    messages := make(chan string, 2)

    messages <- "buffered"
    messages <- "channel"

    fmt.Println(<-messages)
    fmt.Println(<-messages)
}