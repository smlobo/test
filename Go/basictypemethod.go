package main

type Foo interface {
	someFunc() string
}

type BoolAlias bool

func (b BoolAlias) someFunc() string {
	return "someFunc"
}

func callSomeFunc(f Foo) string {
	return f.someFunc()
}

func main() {
	boolVar := BoolAlias(true)
	boolVar.someFunc()
	BoolAlias(true).someFunc()
	callSomeFunc(boolVar)
	callSomeFunc(BoolAlias(false))
}
