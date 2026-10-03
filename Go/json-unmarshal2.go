package main

import (
	"encoding/json"
	"fmt"
	"os"
	"io/ioutil"
)

func main() {

	iFile, _ := os.OpenFile(os.Args[1], os.O_RDONLY, os.ModePerm)
    defer iFile.Close()
    var resultsJson map[string][]map[string]interface{}
    bytes, _ := ioutil.ReadAll(iFile)
    _ = json.Unmarshal(bytes, &resultsJson)
    fmt.Printf("After read from File:\n")
    for key, value := range resultsJson {
        fmt.Printf("  [%s] %s\n", key, value)
    }
    fmt.Printf("results[0]: %s\n", resultsJson["results"][0])
    fmt.Printf("results[0][rows]: %s\n", resultsJson["results"][0]["rows"])

    rowsArray := resultsJson["results"][0]["rows"].([]interface{})
    for i, val := range rowsArray {
    	fmt.Printf("  [%d] %s\n", i, val)
    }

}