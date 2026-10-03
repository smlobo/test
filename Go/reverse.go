package main

import (
  "fmt"
  "os"
)

func main() {
  // Reverse input string
  r := []rune(os.Args[1])
  for i, j := 0, len(r)-1; i < j; i, j = i+1, j-1 {
    r[i], r[j] = r[j], r[i]
  }
  fmt.Printf("Input: %s, Reverse: %s\n", os.Args[1], string(r));
}

