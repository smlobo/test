package main

import (
	"fmt"
	"runtime"
)

func main() {
	switch os := runtime.GOOS; os {
	case "linux":
		fmt.Println("linux")
	case "darwin":
		fmt.Println("OSX")
	default:
		fmt.Println("something else")
	}
}