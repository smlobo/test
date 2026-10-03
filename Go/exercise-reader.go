package main

import "fmt"

type MyReader struct {}

func (m MyReader) Read(b []byte) (int, error) {
	for i, _ := range b {
		b[i] = 'A'
	}
	return len(b), nil
}

func main() {
	myReader := MyReader{}

	var bArray [100]byte
	var c int
	var err error
	var bSlice []byte

	for i := 1; i < 10; i++ {
		bSlice = bArray[0:i]
		c, err = myReader.Read(bSlice)
		fmt.Printf("%d -> %s (%v)\n", c, string(bSlice), err)
	}
}
