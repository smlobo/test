
TARGET = zipkin-example

build:
	go build -o bin/${TARGET} cmd/${TARGET}/main.go

module:
	rm -f go.mod go.sum
	go mod init atny/${TARGET}
	go mod tidy

clean:
	rm -rf bin
	rm -f go.mod go.sum
