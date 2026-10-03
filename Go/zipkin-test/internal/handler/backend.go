package handler

import (
	"fmt"
	"github.com/openzipkin/zipkin-go"
	"log"
	"net/http"
	"time"
)

var BackendPort uint16 = 8001

func BackendHandlerFactory(client *http.Client) func(http.ResponseWriter, *http.Request) {
	return func(w http.ResponseWriter, r *http.Request) {
		log.Println("Received backend request from:", r.Host, r.URL.Path, "::", r.Method)

		for key, value := range r.Header {
			fmt.Println("\t", key, "=>", value)
		}

		w.WriteHeader(http.StatusOK)
		responseBody := fmt.Sprintf("From backend: %s\n", time.Now().Local().Format("2006-01-02_15-04-05.000-0700"))
		w.Write([]byte(responseBody))
	}
}

func BackendHandler(w http.ResponseWriter, r *http.Request) {
	log.Println("Received backend request from:", r.Host, r.URL.Path, "::", r.Method)

	//extractor := b3.ExtractHTTP(r)
	//
	//tracer, err := tracer2.NewTracer("go-backend", BackendPort)
	//if err != nil {
	//	log.Fatal(err)
	//}

	//spanContext := tracer.Extract(extractor)
	//tracer.StartSpanFromContext(spanContext, "backend-processing")

	// do processing
	time.Sleep(500 * time.Millisecond)

	for key, value := range r.Header {
		fmt.Println("\t", key, "=>", value)
	}

	w.WriteHeader(http.StatusOK)
	responseBody := fmt.Sprintf("From backend: %s\n", time.Now().Local().Format("2006-01-02_15-04-05.000-0700"))
	w.Write([]byte(responseBody))
}

func BackendHandler2() http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		log.Println("Received backend request from:", r.Host, r.URL.Path, "::", r.Method)

		// retrieve span from context (created by server middleware)
		span := zipkin.SpanFromContext(r.Context())
		span.Tag("backend_key", "backend value")

		// doing some expensive calculations....
		time.Sleep(25 * time.Millisecond)
		span.Annotate(time.Now(), "backend expensive_calc_done")
		time.Sleep(25 * time.Millisecond)

		w.WriteHeader(http.StatusOK)
		responseBody := fmt.Sprintf("From backend: %s\n", time.Now().Local().Format("2006-01-02_15-04-05.000-0700"))
		w.Write([]byte(responseBody))
	}
}
