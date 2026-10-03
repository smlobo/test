package main

import (
	"fmt"
	"strings"
	"io"
)

type rot13Reader struct {
	r io.Reader
}

func (rot13Reader rot13Reader) Read(b []byte) (int, error) {
	// Read into the given byte slice
	count, err := rot13Reader.r.Read(b)

	// Error
	if err != nil {
		return 0, err
	}

	// Iterate and rotate
	for i := 0; i < count; i++ {
		if (b[i] >= 'A' && b[i] < 'A'+13) || 
		   (b[i] >= 'a' && b[i] < 'a'+13) {
			b[i] += 13
		} else if (b[i] >= 'A'+13 && b[i] <= 'Z') || 
				  (b[i] >= 'a'+13 && b[i] <= 'z') {
			b[i] -= 13			
		}
	}
	return count, err
}

func main() {
	s := strings.NewReader("Lbh penpxrq gur pbqr!")

	byteSlice := make([]byte, 100)
	c, err := s.Read(byteSlice)
	fmt.Printf("original: %d -> %s (%v)\n", c, string(byteSlice[:c]), err)

	dup := strings.NewReader(string(byteSlice[:c]))

	r := rot13Reader{dup}
	c, err = r.Read(byteSlice)
	fmt.Printf("rot13: %d -> %s (%v)\n", c, string(byteSlice[:c]), err)

}
