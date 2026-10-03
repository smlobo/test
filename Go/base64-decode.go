package main

import (
	"encoding/base64"
	"fmt"
	"os"
)

func main() {
	// rawDecodedText, err := base64.URLEncoding.DecodeString(os.Args[1])
	// rawDecodedText, err := base64.RawURLEncoding.DecodeString(os.Args[1])
	rawDecodedText, err := base64.URLEncoding.WithPadding(base64.NoPadding).
		DecodeString(os.Args[1])
	if err != nil {
		fmt.Printf("Error base64 decoding: %v\n", err)
	}
	fmt.Printf("Decoded text: %s\n", rawDecodedText)
}
