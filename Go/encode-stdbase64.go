package main

import (
    "fmt"
    "encoding/base64"
    "io/ioutil"
    "os"
    "strings"
)

func main() {
    if len(os.Args) > 2 {
        fmt.Printf("Usage: %s [<json-string>]\n", os.Args[0])
        os.Exit(-1)
    }

    // Input string from os.Args[1] OR StdIn
    var inputStr string
    if len(os.Args) == 2 {
        inputStr = os.Args[1]
    } else {
        inputBytes, _ := ioutil.ReadAll(os.Stdin)
        inputStr = strings.TrimSpace(string(inputBytes))
    }

    // base 64 encode
    encodedString := base64.StdEncoding.EncodeToString([]byte(inputStr))
    fmt.Printf("%s\n", encodedString)
}
