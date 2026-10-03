package main

import "fmt"

func ping(pings chan<- string, msg string) {
    pings <- fmt.Sprintf("%s+pingsent", msg)
}

func pong(pings <-chan string, pongs chan<- string) {
    msg := fmt.Sprintf("%s+pingreceived", <-pings)
    pongs <- fmt.Sprintf("%s+pongsent", msg)
}

func main() {
    pings := make(chan string, 1)
    pongs := make(chan string, 1)

    // original message sent to pings
    ping(pings, "original")

    // pings receives the msg, and sends it to pong
    pong(pings, pongs)

    // final message sent by pong
    fmt.Printf("%s+pongreceived\n", <-pongs)
}
