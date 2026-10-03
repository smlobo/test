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

func main() {
    for v := range sliceToIterator[int]([]int{1, 3, 5}) {
        println(v)
    }
}
