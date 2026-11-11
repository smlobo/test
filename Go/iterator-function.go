//go:build ignore
package main

import "iter"

func sliceToIterator[T comparable](tSlice []T) iter.Seq[T] {
    return func(yield func(T) bool) {
        for _, v := range tSlice {
            if !yield(v) {
                return
            }
        }
    }
}

func intEvenElementIterator(iSlice []int) iter.Seq[int] {
    return func(yield func(int) bool) {
        for _, v := range iSlice {
        	if v % 2 != 0 {
        		continue
        	}
            if !yield(v) {
                return
            }
        }
    }
}

func main() {
    for v := range sliceToIterator[int]([]int{1, 3, 5}) {
        println(v)
    }
    for v := range intEvenElementIterator([]int{1, 2, 3, 4, 5, 6}) {
        println(v)
    }
}
