package main

/*
#include "mylib.h"
#cgo LDFLAGS: -L${SRCDIR} mylib.a
*/
import "C"
import "fmt"

func main() {
    result := C.myFunction()
    fmt.Println(result.value1, result.value2, result.v3)
}