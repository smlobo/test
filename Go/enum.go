package main

import "fmt"

type SpanType uint8

const (
	ZipkinV2JSON     SpanType = iota + 1
	ZipkinV2Protobuf SpanType = iota + 2
	SpanTypeLength
)

func main() {
	fmt.Println("ZipkinV2JSON:", ZipkinV2JSON)
	fmt.Println("ZipkinV2Protobuf:", ZipkinV2Protobuf)
	fmt.Println("SpanTypeLength:", SpanTypeLength)
}