package main

import (
    "fmt"
    "math/rand"
    "net"
    "os"
    "strings"
    "time"
)

func main() {
    arguments := os.Args
    if len(arguments) == 1 {
        fmt.Println("Please provide a port number!")
        return
    }
    PORT := ":" + arguments[1]

    s, err := net.ResolveUDPAddr("udp", PORT)
    if err != nil {
        fmt.Println(err)
        return
    }

    connection, err := net.ListenUDP("udp", s)
    if err != nil {
        fmt.Println(err)
        return
    }

    defer connection.Close()
    buffer := make([]byte, 10)
    rand.Seed(time.Now().Unix())

    for {
        n, _, _ := connection.ReadFromUDP(buffer)
        fmt.Println("-> ", string(buffer[0:n-1]))

        if strings.TrimSpace(string(buffer[0:n])) == "STOP" {
            fmt.Println("Exiting UDP server!")
            return
        }
    }
}
    
