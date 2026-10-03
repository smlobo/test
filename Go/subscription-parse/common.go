package main

import (
	"log"
	"strings"
)

var environment string

func setEnvironment(input string) {
	if strings.Contains(input, "pendo-ionchef") {
		environment = "pendo-ionchef"
	} else if strings.Contains(input, "pendo-io") {
		environment = "pendo-io"
	} else if strings.Contains(input, "pendo-eu") {
		environment = "pendo-eu"
	} else if strings.Contains(input, "pendo-us1") {
		environment = "pendo-us1"
	} else if strings.Contains(input, "pendo-test") {
		environment = "pendo-test"
	} else {
		log.Fatalf("Unknown environment from: %s", input)
	}
}
