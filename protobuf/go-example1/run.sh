#!/bin/bash

set -x

rm person-pb-pkg/person.pb.go

protoc --go_out=. person-pb-pkg/person.proto
go run main.go

