package main

import (
	"log"
	"net/http"
	//"net/http/httptest"
	//"os"
	"time"

	"github.com/gorilla/mux"

	zipkin "github.com/openzipkin/zipkin-go"
	zipkinhttp "github.com/openzipkin/zipkin-go/middleware/http"
	//logreporter "github.com/openzipkin/zipkin-go/reporter/log"
	httpreporter "github.com/openzipkin/zipkin-go/reporter/http"
)

func main() {
	// set up a span reporter
	//reporter := logreporter.NewReporter(log.New(os.Stderr, "", log.LstdFlags))
	//defer reporter.Close()
	reporter := httpreporter.NewReporter("http://localhost:9411/api/v2/spans")

	// create our local service endpoint
	endpoint, err := zipkin.NewEndpoint("myService", "localhost:0")
	if err != nil {
		log.Fatalf("unable to create local endpoint: %+v\n", err)
	}

	// Sampler tells you which traces are going to be sampled or not. In this case we will record 100% (1.00) of traces.
	sampler, err := zipkin.NewCountingSampler(1)
	if err != nil {
		log.Fatalf("unable to create sampler: %+v\n", err)
	}

	// initialize our tracer
	tracer, err := zipkin.NewTracer(reporter, zipkin.WithSampler(sampler), zipkin.WithLocalEndpoint(endpoint))
	if err != nil {
		log.Fatalf("unable to create tracer: %+v\n", err)
	}

	// create global zipkin http server middleware
	serverMiddleware := zipkinhttp.NewServerMiddleware(
		tracer, zipkinhttp.TagResponseSize(true),
	)

	// create global zipkin traced http client
	client, err := zipkinhttp.NewClient(tracer, zipkinhttp.ClientTrace(true))
	if err != nil {
		log.Fatalf("unable to create client: %+v\n", err)
	}

	// initialize router
	router := mux.NewRouter()

	// start web service with zipkin http server middleware
	//ts := httptest.NewServer(serverMiddleware(router))
	//defer ts.Close()

	// set-up handlers
	//router.Methods("GET").Path("/some_function").HandlerFunc(someFunc(client, ts.URL))
	router.Methods("GET").Path("/some_function").HandlerFunc(someFunc(client, "http://localhost:8081"))
	router.Methods("POST").Path("/other_function").HandlerFunc(otherFunc(client))

	router.Use(serverMiddleware)

	// initiate a call to some_func
	//req, err := http.NewRequest("GET", ts.URL+"/some_function", nil)
	//if err != nil {
	//	log.Fatalf("unable to create http request: %+v\n", err)
	//}
	//
	//res, err := client.DoWithAppSpan(req, "some_function")
	//if err != nil {
	//	log.Fatalf("unable to do http request: %+v\n", err)
	//}
	//res.Body.Close()

	http.ListenAndServe(":8081", router)

}

func someFunc(client *zipkinhttp.Client, url string) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		log.Printf("some_function called with method: %s\n", r.Method)

		// retrieve span from context (created by server middleware)
		span := zipkin.SpanFromContext(r.Context())
		span.Tag("custom_key", "some value")

		// doing some expensive calculations....
		time.Sleep(25 * time.Millisecond)
		span.Annotate(time.Now(), "expensive_calc_done")

		newRequest, err := http.NewRequest("POST", url+"/other_function", nil)
		if err != nil {
			log.Printf("unable to create client: %+v\n", err)
			http.Error(w, err.Error(), 500)
			return
		}

		ctx := zipkin.NewContext(newRequest.Context(), span)

		newRequest = newRequest.WithContext(ctx)

		res, err := client.DoWithAppSpan(newRequest, "other_function")
		if err != nil {
			log.Printf("call to other_function returned error: %+v\n", err)
			http.Error(w, err.Error(), 500)
			return
		}
		res.Body.Close()
	}
}

func otherFunc(client *zipkinhttp.Client) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		log.Printf("other_function called with method: %s\n", r.Method)

		// retrieve span from context (created by server middleware)
		span := zipkin.SpanFromContext(r.Context())
		span.Tag("custom_key", "other value")

		// doing some expensive calculations....
		time.Sleep(25 * time.Millisecond)
		span.Annotate(time.Now(), "other_expensive_calc_done")

		time.Sleep(25 * time.Millisecond)
	}
}
