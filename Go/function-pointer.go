package main

import (
	"fmt"
	"reflect"
)

type My struct{}

func (m My) MyFunc() {
    fmt.Println("MyFunc called")
}

func main() {
    m := My{}
    meth := reflect.ValueOf(m).MethodByName("MyFunc")
    meth.Call(nil)
}
