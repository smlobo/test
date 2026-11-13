//go:build ignore

package main

import "fmt"

type Person struct {
	Name string
	Email string
}

func (p Person) ContactInfo() (string, string) {
	return p.Name, p.Email
}

type Employee struct {
	Person
	Department string
}

func (e Employee) ContactInfo() (string, string, string) {
	return e.Name, e.Email, e.Department
}

func main() {
	e := Employee{
		Name: "Tom",
		Email: "t@g",
		Department: "tech",
	}
	fmt.Printf("Name: %s, Dept: %s\n", e.Name, e.Department)
	n, v := e.Person.ContactInfo()
	fmt.Printf("Person ContactInfo: %s, %s\n", n, v)
	n, v, d := e.ContactInfo()
	fmt.Printf("Employee ContactInfo: %s, %s, %s\n", n, v, d)
}
