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

    // Index of pattern to check next
    pIndex := 0

    // Map of indices found at preceding match
    rowIndices := make(map[int]bool)

    for i := 0; i < len(G); i++ {
        splits := strings.Split(G[i], P[pIndex])

        // Not found
        if len(splits) == 1 {
            pIndex = 0
            rowIndices = make(map[int]bool)
            continue
        }

        fmt.Printf("Found: %d ... %d\n", i, pIndex)

        // Get row indices of matches
        newRowIndices := make(map[int]bool)
        index := 0
        for j := 0; j < len(splits)-1; j++ {
            index += len(splits[j])
            newRowIndices[index] = true
            index += len(P[pIndex])
        }

        // If first pattern row match, add all to the map
        // Else trim the existing map to match new indices
        if pIndex == 0 {
            for rI, _ := range newRowIndices {
                rowIndices[rI] = true
            }
        } else {
            for rI, _ := range rowIndices {
                _, found := newRowIndices[rI]
                if !found {
                    delete(rowIndices, rI)
                }
            }
        }

        // If rowIndices is empty - nothing matched - reset
        if len(rowIndices) == 0 {
            pIndex = 0
        } else {
            pIndex++
        }

        // Found pattern
        if pIndex == len(P) {
            break
        }
    }

    found := "NO"
    if pIndex == len(P) {
        found = "YES"
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
    //     "0",
    // }

    // // test 3
    // G = [...]string {
    //     "12",
    // }
    // P = [...]string {
    //     "",
    // }

    // test 4
    // G := [...]string {
    //     "1234",
    //     "4321",
    // }
    // P := [...]string {
    //     "12",
    //     "21",
    // }

    G := [...]string {
        "123412",
        "561212",
        "123634",
        "781288",
    }
    P := [...]string {
        "12",
        "34",
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
