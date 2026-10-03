package main

import (
    "fmt"
    "encoding/base64"
    "bytes"
    "compress/zlib"
    "io/ioutil"
    "os"
    "strings"
)

func main() {
    if len(os.Args) > 2 {
        fmt.Printf("Usage: %s [<jzb-string>]\n", os.Args[0])
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

    // base 64 decode
    decodedBytes, err := base64.RawURLEncoding.DecodeString(inputStr)
    if err != nil {
        fmt.Printf("err: %v\n", err)
        os.Exit(-1)
    }

    // zlib decompress
    b := bytes.NewReader(decodedBytes)
    z, err := zlib.NewReader(b)
    if err != nil {
        fmt.Printf("err: %v\n", err)
        os.Exit(-1)
    }
    defer z.Close()

    p, err := ioutil.ReadAll(z)
    if err != nil {
        fmt.Printf("err: %v\n", err)
        os.Exit(-1)
    }
    fmt.Printf("%s\n", string(p))
}
