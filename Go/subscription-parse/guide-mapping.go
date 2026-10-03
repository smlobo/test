package main

import (
	"encoding/csv"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"os"
)

type GuideRecord struct {
	Id     string
	Name   string
	Author string
	NewId  string
}

func main() {
	// Usage
	if len(os.Args) < 2 || len(os.Args) > 4 {
		log.Fatalf("Usage: %s <old-guide-csv> <new-guide-json> [<log-file>]\n", os.Args[0])
	}

	// Set logfile output
	if len(os.Args) == 4 {
		logFile, err := os.OpenFile(os.Args[3], os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
		if err != nil {
			log.Fatalf("error opening log file %s: %v", os.Args[2], err)
		}
		defer logFile.Close()
		log.SetOutput(logFile)
	}

	// Environment (GCS project)
	setEnvironment("pendo-io")

	// 12/05/2022 Read csv of guide ids
	// open csv file
	f, err := os.Open(os.Args[1])
	if err != nil {
		log.Fatal(err)
	}
	defer f.Close()

	// read csv values using csv.Reader
	csvReader := csv.NewReader(f)
	data, err := csvReader.ReadAll()
	if err != nil {
		log.Fatal(err)
	}

	// convert records to array of structs
	oldGuideList := createGuideList(data)

	// print the array
	//fmt.Printf("%+v\n", oldGuideList)

	// Read the JSON file
	jsonContent, err := ioutil.ReadFile(os.Args[2])
	if err != nil {
		log.Fatalf("Error when opening file %s: %v", os.Args[2], err)
	}
	newGuideList := decodeGuideJsonStream(jsonContent)
	//fmt.Printf("%+v\n", newGuideList)

	// Iterate over both slices mapping old to new
	guideMap := make(map[string]string)
	for i, oldGuide := range oldGuideList {
		found := false
		for _, newGuide := range newGuideList {
			if oldGuide.Name == newGuide.Name {
				fmt.Printf("[%d] %s %s <-> %s\n", i, oldGuide.Name, oldGuide.Id, newGuide.Id)
				guideMap[oldGuide.Id] = newGuide.Id
				found = true
				break
			}
		}
		if !found {
			fmt.Printf("NOT FOUND: [%d] %v\n", i, oldGuide)
		}
	}

	// Write the map to JSON file
	oFile, _ := os.OpenFile("./MobileCause.GiveSmart_by_Community_B.mapping.json",
		os.O_CREATE|os.O_WRONLY, os.ModePerm)
	defer oFile.Close()
	enc := json.NewEncoder(oFile)
	err = enc.Encode(guideMap)
	//fmt.Printf(" encode errors: %v\n", err)
}

func createGuideList(data [][]string) []GuideRecord {
	var guides []GuideRecord

	//fmt.Printf("CSV length: %d\n", len(data))

	for _, line := range data {
		//fmt.Printf("[%d] %s\n", i, line)
		var rec GuideRecord
		for j, field := range line {
			if j == 0 {
				rec.Id = field
			} else if j == 1 {
				rec.Name = field
			} else if j == 3 {
				rec.Author = field
			}
		}
		guides = append(guides, rec)
	}

	//fmt.Printf("CSV length2: %d\n", len(guides))

	return guides
}

func decodeGuideJsonStream(jsonStream []byte) []Guide {
	var guides []Guide

	//dec := json.NewDecoder(bytes.NewReader(jsonStream))
	//for {
	//	var guide Guide
	//	if err := dec.Decode(&guide); err == io.EOF {
	//		break
	//	} else if err != nil {
	//		log.Fatal(err)
	//	}
	//	//log.Printf("  %s: %s\n", sub.Name, sub.Id)
	//	guides = append(guides, guide)
	//}

	_ = json.Unmarshal(jsonStream, &guides)
	return guides
}
