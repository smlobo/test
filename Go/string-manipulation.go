package main

import (
	"fmt"
	"time"
	"os"
	"strings"
	"regexp"
)

func main() {
	// currentTime := time.Now().Local()
	// newYork, _ := time.LoadLocation("America/New_York")
	// currentTime := time.Now().In(newYork)
	// addisAbaba, _ := time.LoadLocation("Africa/Addis_Ababa")
	// currentTime := time.Now().In(addisAbaba)
	japan, _ := time.LoadLocation("Japan")
	// currentTime := time.Now().In(japan)
	chicago, _ := time.LoadLocation("America/Chicago")
	currentTime := time.Now().In(chicago)

	// Sanitized time stamp - for time zones ahead of UTC, replace + with =
	sanitizedTimeStamp := currentTime.Format("2006-01-02_15-04-05.000-0700")
	sanitizedTimeStamp = strings.Replace(sanitizedTimeStamp, "+", "=", -1)

	dataDir := "data"
	filePrefix := "continuoustrace"
	hostname := "foo"
	id := "ostjaegerthrift@eds-query-169.254.1.2"
	fileSuffix := "apptrace"

	// File name format: continuoustrace@YYYY-MM-DD_HH.mm.ss.mmm-TZ@<hostname>@<spanType>@
	// <someID>.<PID>.<rollCount>.apptrace
	fileName := fmt.Sprintf("%s/%s@%s@%s@%s.%d.0.%s", dataDir, filePrefix, sanitizedTimeStamp,
		hostname, id, os.Getpid(), fileSuffix)

	fmt.Printf("Gen filename: %s\n", fileName)

	newTime := time.Now().In(japan)
	sanitizedTimeStamp = newTime.Format("2006-01-02_15-04-05.000-0700")
	sanitizedTimeStamp = strings.Replace(sanitizedTimeStamp, "+", "=", -1)

	re := regexp.MustCompile(`\d{4}-\d{2}-\d{2}_\d{2}-\d{2}-\d{2}\.\d{3}(\-|=)\d{4}`)
	newFileName := re.ReplaceAllString(fileName, sanitizedTimeStamp)

	fmt.Printf("New filename: %s\n", newFileName)

	short := 6166
	long := 1234567890123
	fmt.Printf("short: %d, long: %d, long: %ld\n", short, long, long)
}