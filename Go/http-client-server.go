//go:build ignore
package main

import (
	"fmt"
	"io"
	"net/http"
	"runtime"
	"strconv"
	"time"
)

func server(port int) {
    fmt.Printf("Starting Golang server on port %d\n", port)
    countryServer := http.NewServeMux()

    count := 0
    handlerFunc := func(writer http.ResponseWriter, request *http.Request) {
        if request.Method != "GET" {
            writer.WriteHeader(http.StatusBadRequest)
            return
        }
        fmt.Printf("[%d] Received %s request from: %s\n", count, request.URL.Path,
            request.UserAgent())
        count++
        writer.Header().Add("Server", runtime.Version())
        writer.WriteHeader(http.StatusOK)
        fmt.Printf("Got path: %s\n", request.URL.Path)
        val, _ := strconv.Atoi(request.URL.Path[1:])
        _, _ = writer.Write([]byte(strconv.Itoa(val+1)))
    }
    countryServer.HandleFunc("/", handlerFunc)

    go http.ListenAndServe(":"+strconv.Itoa(port), countryServer)
}

func makeRequest(port int, count int) {
	restPath := fmt.Sprintf("/%d", count)
    response, err := http.Get("http://:" + strconv.Itoa(port) + restPath)
    if err != nil {
        fmt.Printf("[%d] Failed", count)
    }
    body, err := io.ReadAll(response.Body)
    fmt.Printf("[%d] To: %d; Got: %d {%s} : %s\n", count, port, response.StatusCode,
        response.Header.Get("Server"), string(body))
    count++
    _ = response.Body.Close()
    time.Sleep(time.Second)
}

func main() {
	port := 1234
	server(port)

    fmt.Printf("Sending request to port: %d\n", port)

    // Keep hitting endpoints indefinitely
    count := 0
    for {
        makeRequest(port, count)
        count++
    }
}
