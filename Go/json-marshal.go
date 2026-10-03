package main

import (
	"encoding/json"
	"fmt"
)

type bindStruct struct {
	XStr string
	YMap map[string] string
}

func main() {
	// object 1 : all field init
	b1 := bindStruct{
		XStr: "xxx",
		YMap: make(map[string]string),
	}
	b1.YMap["aaa"] = "ppp"
	b1.YMap["bbb"] = "qqq"
	m1, _ := json.Marshal(b1)
	fmt.Printf("%+v marshaled: %s\n", b1, string(m1))
	m1ymap, _ := json.Marshal(b1.YMap)
	fmt.Printf("  %+v marshaled: %s\n", b1.YMap, string(m1ymap))
	var b1prime bindStruct
	_ = json.Unmarshal(m1, &b1prime)
	fmt.Printf("  -> unmarshaled struct: %+v\n", b1prime)
	var b1ymap map[string]string
	_ = json.Unmarshal(m1ymap, &b1ymap)
	fmt.Printf("  -> unmarshaled map: %+v\n\n", b1ymap)

	// object 2 : map not init
	b2 := bindStruct{
		XStr: "xxxx",
	}
	m2, _ := json.Marshal(b2)
	fmt.Printf("%+v marshaled: %s\n", b2, string(m2))
	m2ymap, _ := json.Marshal(b2.YMap)
	fmt.Printf("  %+v marshaled: %s\n", b2.YMap, string(m2ymap))
	var b2prime bindStruct
	_ = json.Unmarshal(m2, &b2prime)
	fmt.Printf("  -> unmarshaled struct: %+v\n", b2prime)
	var b2ymap map[string]string
	_ = json.Unmarshal(m2ymap, &b2ymap)
	fmt.Printf("  -> unmarshaled map: %+v\n\n", b2ymap)

	// object 3 : map not init, special handling for nil
	b3 := bindStruct{
		XStr: "x333",
	}
	m3, _ := json.Marshal(b3)
	fmt.Printf("%+v marshaled: %s\n", b3, string(m3))
	var m3ymap []byte
	if b3.YMap == nil {
		fmt.Printf("  %+v marshaled: %s\n", b3.YMap, string(m3ymap))		
	}
	var b3prime bindStruct
	_ = json.Unmarshal(m3, &b3prime)
	fmt.Printf("  -> unmarshaled struct: %+v\n", b3prime)
	if string(m3ymap) == "" {
		fmt.Printf("  DO NOT unmarshal empty string\n")
	}
}