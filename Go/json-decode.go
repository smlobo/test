package main

import (
    "encoding/json"
    "fmt"
    "io"
    "log"
    "bytes"
)

type Message struct {
    Name, Text string
}

func main() {
    const jsonStream = `
    {"Name": "Ed", "Text": "Knock knock."}
    {"Name": "Sam", "Text": "Who's there?"}
    {"Name": "Ed", "Text": "Go fmt."}
    {"Name": "Sam", "Text": "Go fmt who?"}
    {"Name": "Ed", "Text": "Go fmt yourself!"}
    `

    fmt.Printf("Initial static string JSON decode:\n")
    decodeJsonStream([]byte(jsonStream))

    // Create Messages & encode to buffer
    byteBuffer := bytes.Buffer{}
    encoder := json.NewEncoder(&byteBuffer)
    for i := 0; i < 3; i++ {
        message := Message{Name: fmt.Sprintf("xx%d", i), Text: "qqqq"}
        encoder.Encode(message)
    }
    fmt.Printf("Decode from structs JSON encoded:\n")
    decodeJsonStream(byteBuffer.Bytes())

    // Single struct
    message := Message{Name: "Foo", Text: "Bar"}
    singleMessage, _ := json.Marshal(message)
    fmt.Printf("Single struct JSON marshaled: <%s>\n", string(singleMessage))
    decodeJsonStream(singleMessage)

    // Local struct
    type fooBar struct {
        foo, bar string
    }
    myFooBar := fooBar{foo: "qqq", bar: "www"}
    jsonFooBar, _ := json.Marshal(myFooBar)
    fmt.Printf("Single local struct JSON marshal: %s\n", string(jsonFooBar))

    // Local exported struct
    type F2B2 struct {
        f2, b2 string
    }
    myF2B2 := F2B2{f2: "aaa", b2: "sss"}
    jsonFooBar, _ = json.Marshal(myF2B2)
    fmt.Printf("Single exported local struct JSON marshal: %s\n", string(jsonFooBar))

    // Local non-exported struct, exported fields
    type f3b3 struct {
        F3, B3 string
    }
    myF3B3 := f3b3{F3: "zzz", B3: "xxx"}
    jsonFooBar, _ = json.Marshal(myF3B3)
    fmt.Printf("Single local struct exported field JSON marshal: %s\n", string(jsonFooBar))

    // Nested struct
    type xxx struct {
        Qq, Ww string
    }
    type yyy struct {
        Aa, Ss string
    }
    type xy struct {
        Xxx xxx
        Yyy yyy
    }
    myXxx := xxx{Qq: "111", Ww: "222"}
    myYyy := yyy{Aa: "333", Ss: "444"}
    myXY := xy{Xxx: myXxx, Yyy: myYyy}
    jsonFooBar, _ = json.Marshal(myXY)
    fmt.Printf("Nested JSON marshal: %s\n", string(jsonFooBar))
}

func decodeJsonStream(jsonStream []byte) {
    dec := json.NewDecoder(bytes.NewReader(jsonStream))
    for {
        var m Message
        if err := dec.Decode(&m); err == io.EOF {
            break
        } else if err != nil {
            log.Fatal(err)
        }
        fmt.Printf("  %s: %s\n", m.Name, m.Text)
    }
}
