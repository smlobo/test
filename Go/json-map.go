package main

import (
    "encoding/json"
    "fmt"
    "io"
    "bytes"
    "log"
    "os"
    // "bufio"
    "io/ioutil"
)

func main() {
    var jsonBlob = []byte(`{"foo":"x","bar":"y"},{"foo":"xx"}`)
    var rawData map[string]string
    err := json.Unmarshal(jsonBlob, &rawData)
    if err != nil {
        fmt.Println("error:", err)
    }
    fmt.Printf("%+v\n", rawData)
    fmt.Printf("array len: %d\n", len(rawData))
    for key, value := range rawData {
        fmt.Printf("[%s] %s\n", key, value)
    }

    jsonStream := `
    {"Ed": "Knock knock."}
    {"Sam": "Who's there?"}
    {"Ed": "Go fmt."}
    {"Sam": "Go fmt who?"}
    {"Ed": "Go fmt yourself!"}
    `
    var m map[string]string
    dec := json.NewDecoder(bytes.NewReader([]byte(jsonStream)))
    for {
        if err := dec.Decode(&m); err == io.EOF {
            break
        } else if err != nil {
            log.Fatal(err)
        }
        // fmt.Printf("  %s: %s\n", m.Name, m.Text)
    }
    for key, value := range m {
        fmt.Printf("  [%s] %s\n", key, value)
    }

    // oFile, _ := os.Create("./map.txt")
    oFile, _ := os.OpenFile("./map2.txt", os.O_CREATE|os.O_WRONLY, os.ModePerm)
    defer oFile.Close()
    // w := bufio.NewWriter(oFile)
    enc := json.NewEncoder(oFile)
    err = enc.Encode(m)
    fmt.Printf(" encode errors: %v\n", err)
    // err = oFile.Sync()
    // fmt.Printf(" sync errors: %v\n", err)

    // Read it back
    iFile, _ := os.OpenFile("./map2.txt", os.O_RDONLY, os.ModePerm)
    defer iFile.Close()
    var visitorJson map[string]string
    bytes, _ := ioutil.ReadAll(iFile)
    err = json.Unmarshal(bytes, &visitorJson)
    fmt.Printf("After read from File:\n")
    for key, value := range visitorJson {
        fmt.Printf("  [%s] %s\n", key, value)
    }

    // Read it back my style
    var m2 map[string]string
    iFile2, _ := os.OpenFile("./map2.txt", os.O_RDONLY, os.ModePerm)
    defer iFile2.Close()
    dec = json.NewDecoder(iFile2)
    for {
        if err := dec.Decode(&m2); err == io.EOF {
            break
        } else if err != nil {
            log.Fatal(err)
        }
        // fmt.Printf("  %s: %s\n", m.Name, m.Text)
    }
    fmt.Printf("After read from File 2:\n")
    for key, value := range m2 {
        fmt.Printf("  [%s] %s\n", key, value)
    }

}
