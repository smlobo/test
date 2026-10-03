package main

import (
	"fmt"
	"time"
)

func main() {
	timeString := "2020-01-01T09:15:25.789Z"
	parseTime, err := time.Parse(time.RFC3339Nano, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}


	timeString = "2020-01-01T09:15:25.789789789Z"
	parseTime, err = time.Parse(time.RFC3339Nano, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

	timeString = "2020-01-01T09:15:25.789+04:00"
	parseTime, err = time.Parse(time.RFC3339Nano, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

	timeString = "2020-01-01T09:15:25.789-08:00"
	parseTime, err = time.Parse(time.RFC3339Nano, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

	timeString = "2020-01-01T09:15:25.789987Z"
	parseTime, err = time.Parse(time.RFC3339Nano, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

	timeString = "2020-01-01T09:15:25-04:00"
	parseTime, err = time.Parse(time.RFC3339Nano, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

	timeString = "2022-01-01T09:15:2Z"
	parseTime, err = time.Parse(time.RFC3339, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

	timeString = "2022-01-01T09:15:02Z"
	parseTime, err = time.Parse(time.RFC3339, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

	timeString = "2017-08-31T00:00:00Z"
	parseTime, err = time.Parse(time.RFC3339, timeString)
	if err != nil {
		fmt.Printf("failed parse: %s\n", timeString)
	} else {
		fmt.Printf("Parsed to: %s\n", parseTime)
	}

}
