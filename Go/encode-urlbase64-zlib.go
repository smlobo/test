package main

import (
    "fmt"
    "encoding/base64"
    "io/ioutil"
    "os"
    "compress/zlib"
    "bytes"
    "strings"
)

func main() {
    if len(os.Args) > 2 {
        fmt.Printf("Usage: %s [<json-string>|<file-name>]\n", os.Args[0])
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
    // inputBytes := []byte(strings.ReplaceAll(inputStr, " ", ""))
    inputBytes := []byte(inputStr)

    // zlib compress
    var compressedBytes bytes.Buffer
    w := zlib.NewWriter(&compressedBytes)
    w.Write(inputBytes)
    w.Close()

    // base 64 encode
    encodedString := base64.RawURLEncoding.EncodeToString(compressedBytes.Bytes())
    fmt.Printf("%s\n", encodedString)
}
