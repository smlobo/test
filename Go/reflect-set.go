package main

import (
	"reflect"
	"unsafe"
	"fmt"
)

type Var struct {
	name string
}

type Type interface {
	floatMe(int) float32
}

type XType struct {
	x int
}

func (x XType) floatMe(a int) float32 {
	return float32(x.x + a)
}

type YType struct {
	y float32
}

func (y YType) floatMe(a int) float32 {
	return y.y + float32(a)
}

type Parameter struct {
	name      string
	object    *Var // non-nil
	typ       Type
	referrers []Var
}

func main() {
	p := Parameter{
		name: "Alice",
		object: &Var{"AliceVar"},
		typ: XType{5},
		referrers: nil,
	}

	// Get the reflect.Value of p
	v := reflect.ValueOf(&p).Elem()

	// Find the field by name
	field := v.FieldByName("name")

	// Make the field accessible
	field = reflect.NewAt(field.Type(), unsafe.Pointer(field.UnsafeAddr())).Elem()

	// Set the value
	field.SetString("Bob")

	fmt.Printf("%s, {%p} %v, %.2f, %v\n", p.name, p.object, *p.object, 
		p.typ.floatMe(1), p.referrers)

	objField := v.FieldByName("object")
	objField = reflect.NewAt(objField.Type(), unsafe.Pointer(field.UnsafeAddr())).Elem()
	objField.
}
