package main

import (
	"encoding/json"
	"fmt"
	"log"
)

func main() {
	type Club struct {
		Name string `json:"clubname"`
		Championships int `json:"championships,omitempty"`
		Country string
	}

	var clubs = []Club {
		{"Paris St Germain", 2, "France"},
		{"Bayern Munich", 5, "Germany"},
		{"Porto", 0, "Portugal"},
		{"Arsenal", 1, "England"},
	}

	// Marshal
	data, err := json.MarshalIndent(clubs, "", "  ")
	if err != nil {
		log.Fatalf("JSON marshaling failed: %s", err)
	}
	fmt.Printf("JSON marshal (indent):\n%s\n", data)

	// Unmarshal
	var clubs2 []Club
	err = json.Unmarshal(data, &clubs2)
	if err != nil {
		log.Fatalf("JSON unmarshaling failed: %s", err)
	}
	fmt.Printf("JSON unmarshal:\n%s\n", data)
	for index, value := range clubs2 {
		fmt.Printf("[%d] %v\n", index, value)
	}
}
