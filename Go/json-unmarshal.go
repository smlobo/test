package main

import (
	"encoding/json"
	"fmt"
	"os"
)

func main() {
	fmt.Printf("%s\n", os.Args[1])
	var jsonBlob = []byte(os.Args[1])
	var rawData []map[string]interface{}
	err := json.Unmarshal(jsonBlob, &rawData)
	if err != nil {
		fmt.Println("error:", err)
	}
	fmt.Printf("%+v\n", rawData)
	fmt.Printf("array len: %d\n", len(rawData))
	for index, rD := range rawData {
		fmt.Printf("[%d] %s\n", index, rD["visitor_id"])
	}
}