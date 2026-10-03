package tracer

import (
	"github.com/openzipkin/zipkin-go"
	"github.com/openzipkin/zipkin-go/model"
	reporterhttp "github.com/openzipkin/zipkin-go/reporter/http"
)

const endpointURL = "http://localhost:9411/api/v2/spans"

func NewTracer(serviceName string, port uint16) (*zipkin.Tracer, error) {
	// Protobuf serializer
	//rO := reporterhttp.Serializer(zipkin_proto3.SpanSerializer{})

	// The reporter sends traces to zipkin server
	reporter := reporterhttp.NewReporter(endpointURL)
	//reporter := reporterhttp.NewReporter(endpointURL, rO)

	// Local endpoint represent the local service information
	localEndpoint := &model.Endpoint{ServiceName: serviceName, Port: port}

	// Sampler tells you which traces are going to be sampled or not. In this case we will record 100% (1.00) of traces.
	sampler, err := zipkin.NewCountingSampler(1)
	if err != nil {
		return nil, err
	}

	t, err := zipkin.NewTracer(
		reporter,
		zipkin.WithSampler(sampler),
		zipkin.WithLocalEndpoint(localEndpoint),
	)
	if err != nil {
		return nil, err
	}

	return t, err
}
