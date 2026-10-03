package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

/*
 * Complete the 'surfaceArea' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts 2D_INTEGER_ARRAY A as parameter.
 */

func print2DSlice(x [][]int32) {
    for i := 0; i < len(x); i++ {
        for j := 0; j < len(x[i]); j++ {
            fmt.Printf("%d,", x[i][j])
        }
        fmt.Println()
    }
}

func surfaceArea(A [][]int32) int32 {
    // Write your code here

    print2DSlice(A)

    var count int32

    // Slice of each cell surface area
    var cell [][]int32
    for i := 0; i < len(A); i++ {
        var row []int32
        for j := 0; j < len(A[i]); j++ {
            cellSA := 6 * A[i][j] - 2 * (A[i][j] - 1)
            count += cellSA
            row = append(row, cellSA)
        }
        cell = append(cell, row)
    }
    print2DSlice(cell)
    fmt.Printf("before jam: %d\n", count)

    // Jam columns together
    for i := 0; i < len(cell); i++ {
        for j := 1; j < len(cell[i]); j++ {
            if A[i][j-1] < A[i][j] {
                count -= A[i][j-1] * 2
            } else {
                count -= A[i][j] * 2
            }
        }
    }
    fmt.Printf("after col: %d\n", count)

    // Jam rows together
    for i := 1; i < len(cell); i++ {
        for j := 0; j < len(cell[i]); j++ {
            if A[i-1][j] < A[i][j] {
                count -= A[i-1][j] * 2
            } else {
                count -= A[i][j] * 2
            }
        }
    }

    return count
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    // stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    // checkError(err)

    // defer stdout.Close()

    // writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    firstMultipleInput := strings.Split(strings.TrimSpace(readLine(reader)), " ")

    HTemp, err := strconv.ParseInt(firstMultipleInput[0], 10, 64)
    checkError(err)
    H := int32(HTemp)

    WTemp, err := strconv.ParseInt(firstMultipleInput[1], 10, 64)
    checkError(err)
    W := int32(WTemp)

    var A [][]int32
    for i := 0; i < int(H); i++ {
        ARowTemp := strings.Split(strings.TrimRight(readLine(reader)," \t\r\n"), " ")

        var ARow []int32
        for _, ARowItem := range ARowTemp {
            AItemTemp, err := strconv.ParseInt(ARowItem, 10, 64)
            checkError(err)
            AItem := int32(AItemTemp)
            ARow = append(ARow, AItem)
        }

        if len(ARow) != int(W) {
            panic("Bad input")
        }

        A = append(A, ARow)
    }

    result := surfaceArea(A)
    fmt.Printf("Result: %d\n", result)

    // fmt.Fprintf(writer, "%d\n", result)

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
