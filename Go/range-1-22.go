package main

func main() {
	var prints []func()
	for i := 0; i < 5; i++ {
		prints = append(prints, func() { println(i) })
		i++
	}
	for _, p := range prints {
		p()
	}

	for j := range 10 {
		println(j)
	}
}
