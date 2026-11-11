//go:build ignore

package main

import "fmt"

type Stringer interface {
    String() string
}

type User struct {
    Name string
}

func (u *User) String() string {
    if u == nil {
        return "<nil User>"
    }
    return u.Name
}

func describe(s Stringer) string {
	// nil interface
	if s == nil {
		return "no value"
	}

	user, ok := s.(*User)
	if ok && user == nil {
		fmt.Printf("describe(): would have returned: %s\n", s.String())
		return "no value"
	}

	return s.String()
}

func main() {
	// nil interface
	fmt.Printf("nil interface: %s\n", describe(nil))

	// nil struct
	var nilUser *User
	fmt.Printf("nil interface struct: %s\n", describe(nilUser))

	// valid struct
	var validUser *User
	validUser = &User{Name: "Axl"}
	fmt.Printf("valid interface struct: %s\n", describe(validUser))
}
