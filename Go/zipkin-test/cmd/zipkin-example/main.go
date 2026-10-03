
package main

import (
	tracer2 "atny/zipkin-example/internal/tracer"
	"atny/zipkin-example/internal/handler"
	"fmt"
	"os"

	"github.com/gorilla/mux"
	"log"
	"net/http"

	zipkinhttp "github.com/openzipkin/zipkin-go/middleware/http"
)

func setupFrontend() {

	tracer, err := tracer2.NewTracer("go-frontend", handler.FrontendPort)
	if err != nil {
		log.Fatal(err)
	}

	router := mux.NewRouter()

	// create global zipkin http server middleware
	serverMiddleware := zipkinhttp.NewServerMiddleware(
		tracer, zipkinhttp.TagResponseSize(true),
	)

	// create global zipkin traced http client
	client, err := zipkinhttp.NewClient(tracer, zipkinhttp.ClientTrace(true))
	if err != nil {
		log.Fatalf("unable to create client: %+v\n", err)
	}

	// Add the instrumented transport to the defaultClient
	// that comes with the zipkin-go library
	//http.DefaultClient.Transport, err = zipkinhttp.NewTransport(
	//	tracer,
	//	zipkinhttp.TransportTrace(true),
	//)
	//if err != nil {
	//	log.Fatal(err)
	//}

	//router.HandleFunc("/", handler.FrontendHandlerFactory(http.DefaultClient))
	//router.HandleFunc("/", handler.FrontendHandlerFactory(client))
	//router.HandleFunc("/", handler.FrontendHandler)
	router.Methods("GET").Path("/").HandlerFunc(handler.FrontendHandler2(client))

	//router.Use(zipkinhttp.NewServerMiddleware(
	//	tracer,
	//	zipkinhttp.SpanName("request")), // name for request span
	//)
	router.Use(serverMiddleware)

	log.Println("Started frontend at :", handler.FrontendPort)
	portString := fmt.Sprintf(":%d", handler.FrontendPort)
	log.Fatal(http.ListenAndServe(portString, router))
}


func setupBackend() {

	tracer, err := tracer2.NewTracer("go-backend", handler.BackendPort)
	if err != nil {
		log.Fatal(err)
	}

	router := mux.NewRouter()

	// create global zipkin http server middleware
	serverMiddleware := zipkinhttp.NewServerMiddleware(
		tracer, zipkinhttp.TagResponseSize(true),
	)

	//// Add the instrumented transport to the defaultClient
	//// that comes with the zipkin-go library
	//http.DefaultClient.Transport, err = zipkinhttp.NewTransport(
	//	tracer,
	//	zipkinhttp.TransportTrace(true),
	//)
	//if err != nil {
	//	log.Fatal(err)
	//}
	//
	////r.HandleFunc("/", handler.BackendHandlerFactory(http.DefaultClient))
	//r.HandleFunc("/", handler.BackendHandler)
	router.Methods("POST").Path("/").HandlerFunc(handler.BackendHandler2())

	//r.Use(zipkinhttp.NewServerMiddleware(
	//	tracer,
	//	zipkinhttp.SpanName("request")), // name for request span
	//)
	router.Use(serverMiddleware)

	log.Println("Started backend at :", handler.BackendPort)
	portString := fmt.Sprintf(":%d", handler.BackendPort)
	log.Fatal(http.ListenAndServe(portString, router))
}

func main() {

	if os.Args[1] == "frontend" {
		setupFrontend()
	} else if os.Args[1] == "backend" {
		setupBackend()
	}

	//tracer, err := tracer2.NewTracer("my-service", 8081)
	//if err != nil {
	//	log.Fatal(err)
	//}
	////
	////// Add the instrumented transport to the defaultClient
	////// that comes with the zipkin-go library
	//////http.DefaultClient.Transport, err = zipkinhttp.NewTransport(
	//////	tracer,
	//////	zipkinhttp.TransportTrace(true),
	//////)
	//////if err != nil {
	//////	log.Fatal(err)
	//////}
	////
	//r := mux.NewRouter()
	////
	//////r.HandleFunc("/", handler.HomeHandlerFactory(http.DefaultClient))
	////
	//r.HandleFunc("/foo", handler.FooHandler)
	//r.Use(zipkinhttp.NewServerMiddleware(
	//	tracer,
	//	zipkinhttp.SpanName("request")), // name for request span
	//)
	//
	//log.Println("Hi - hit me at ...:8081/foo")
	//log.Fatal(http.ListenAndServe(":8081", r))
}
