package main

import (
    "bufio"
    "fmt"
    "io"
    // "os"
    // "strconv"
    "strings"
)

/*
 * Complete the 'gridSearch' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING_ARRAY G
 *  2. STRING_ARRAY P
 */

func printStringSlice(x []string) {
    for i := 0; i < len(x); i++ {
        fmt.Println(x[i])
    }
}

func gridSearch(G []string, P []string) string {
    // Write your code here

    found := "NO"

    // Iterate over grid
    for i := 0; i < len(G); i++ {
        found = "NO"

        // Map of indices found at preceding match
        rowIndices := make(map[int]bool)

        for j := 0; j < len(P) && (i+j) < len(G); j++ {

            // Find all matches
            splits := strings.Split(G[i+j], P[j])

            // Not found
            if len(splits) == 1 {
                rowIndices = make(map[int]bool)
                break
            }

            // Get row indices of matches
            newRowIndices := make(map[int]bool)
            index := 0
            for j := 0; j < len(splits)-1; j++ {
                index += len(splits[j])
                newRowIndices[index] = true
                index += len(P[j])
            }

            // If first pattern row match, add all to the map
            // Else trim the existing map to match new indices
            if j == 0 {
                for rI, _ := range newRowIndices {
                    rowIndices[rI] = true
                }
            } else {
                for rI, _ := range rowIndices {
                    _, ok := newRowIndices[rI]
                    if !ok {
                        delete(rowIndices, rI)
                    }
                }
            }

            // If rowIndices is empty - nothing matched - go to next grid line
            if len(rowIndices) == 0 {
                break
            }
        }

        // Found pattern
        if len(rowIndices) > 0 {
            found = "YES"
            break
        }
    }

    return found
}

func main() {
    // reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    // stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    // checkError(err)

    // defer stdout.Close()

    // writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    // tTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    // checkError(err)
    // t := int32(tTemp)

    // for tItr := 0; tItr < int(t); tItr++ {
    //     firstMultipleInput := strings.Split(strings.TrimSpace(readLine(reader)), " ")

    //     RTemp, err := strconv.ParseInt(firstMultipleInput[0], 10, 64)
    //     checkError(err)
    //     R := int32(RTemp)

    //     CTemp, err := strconv.ParseInt(firstMultipleInput[1], 10, 64)
    //     checkError(err)
    //     C := int32(CTemp)

    //     var G []string

    //     for i := 0; i < int(R); i++ {
    //         GItem := readLine(reader)
    //         G = append(G, GItem)
    //     }

    //     secondMultipleInput := strings.Split(strings.TrimSpace(readLine(reader)), " ")

    //     rTemp, err := strconv.ParseInt(secondMultipleInput[0], 10, 64)
    //     checkError(err)
    //     r := int32(rTemp)

    //     cTemp, err := strconv.ParseInt(secondMultipleInput[1], 10, 64)
    //     checkError(err)
    //     c := int32(cTemp)

    //     var P []string

    //     for i := 0; i < int(r); i++ {
    //         PItem := readLine(reader)
    //         P = append(P, PItem)
    //     }

    // test 1
    // G := [...]string {
    //     "7283455864",
    //     "6731158619",
    //     "8988242643",
    //     "3830589324",
    //     "2229505813",
    //     "5633845374",
    //     "6473530293",
    //     "7053106601",
    //     "0834282956",
    //     "4607924137",
    // }
    // P := [...]string {
    //     "9505",
    //     "3845",
    //     "3530",
    // }

    // test 2
    // G := [...]string {
    //     "1",
    // }
    // P := [...]string {
    //     "1",
    // }

    // test 3
    // G := [...]string {
    //     "12",
    // }
    // P := [...]string {
    //     "",
    // }

    // test 4
    // G := [...]string {
    //     "1234",
    //     "4321",
    // }
    // P := [...]string {
    //     "12",
    //     "43",
    // }

    // G := [...]string {
    //     "123412",
    //     "561212",
    //     "123634",
    //     "781288",
    // }
    // P := [...]string {
    //     "12",
    //     "34",
    // }

    // test 3
    G := [...]string {
        "111111111111111",
        "111111111111111",
        "111111111111111",
        "111111011111111",
        "111111111111111",
        "111111111111111",
        "101010101010101",
    }
    P := [...]string {
        "11111",
        "11111",
        "11111",
        "11110",
    }

    printStringSlice(G[:])
    printStringSlice(P[:])

    result := gridSearch(G[:], P[:])

    // fmt.Fprintf(writer, "%s\n", result)
    fmt.Printf("Result: %s\n", result)
    // }

    // writer.Flush()
}

func readLine(reader *bufio.Reader) string {
    str, _, err := reader.ReadLine()
    if err == io.EOF {
        return ""
    }

    return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
    if err != nil {
        panic(err)
    }
}
