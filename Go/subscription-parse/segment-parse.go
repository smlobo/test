package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"os"
)

func main() {
	// Usage
	if len(os.Args) < 2 || len(os.Args) > 3 {
		log.Fatalf("Usage: %s <segment-json> [<log-file>]\n", os.Args[0])
	}

	segmentJsonFilename := os.Args[1]

	// Set logfile output
	if len(os.Args) == 4 {
		logFile, err := os.OpenFile(os.Args[3], os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
		if err != nil {
			log.Fatalf("error opening log file %s: %v", os.Args[2], err)
		}
		defer logFile.Close()
		log.SetOutput(logFile)
	}

	// Read the JSON file
	jsonContent, err := ioutil.ReadFile(segmentJsonFilename)
	if err != nil {
		log.Fatalf("Error when opening file %s: %v", segmentJsonFilename, err)
	}
	var segmentJson []map[string]interface{}
	err = json.Unmarshal(jsonContent, &segmentJson)
	if err != nil {
		log.Fatalf("Error unmarshal json %s: %v", segmentJsonFilename, err)
	}

	sfdcSegmentMap := make(map[string]bool)

	// Iterate
	for _, segment := range segmentJson {
		//fmt.Printf("[%d] %v\n", index, segment["name"])
		def, ok := segment["definition"]
		if !ok {
			log.Printf("definition not found: %v\n", segment["name"])
			continue
		}
		defMap, ok := def.(map[string]interface{})
		if !ok {
			log.Printf("definition not map: %v\n", segment["name"])
			continue
		}
		filters, ok := defMap["filters"]
		if !ok {
			log.Printf("filters not found: %v\n", segment["name"])
			continue
		}
		filtersSlice, ok := filters.([]interface{})
		if !ok {
			log.Printf("filters not slice: %v\n", segment["name"])
			continue
		}

		createdUser := segment["createdByUser"].(map[string]interface{})["username"]
		updatedUser := segment["lastUpdatedByUser"].(map[string]interface{})["username"]

		for _, filter := range filtersSlice {
			filterMap, ok := filter.(map[string]interface{})
			if !ok {
				log.Printf("filter not map: %v\n", segment["name"])
				continue
			}
			//fmt.Printf("  def; filters; field = %v\n", filterMap["field"])
			group, ok := filterMap["group"]
			if !ok {
				//log.Printf("filter group not found: %v\n", segment["name"])
				continue
			}
			groupStr, ok := group.(string)
			if !ok {
				log.Printf("filter group not string: %v\n", segment["name"])
				continue
			}
			if groupStr == "salesforce" {
				fmt.Printf("\"%v\"; %v; %v; %v; %v\n", segment["name"],
					segment["id"], filterMap["field"], createdUser, updatedUser)
				sfdcSegmentMap[segment["id"].(string)] = true
			}
		}
	}

	// Write out unique sfdc segments
	//for key, _ := range sfdcSegmentMap {
	//	fmt.Printf("%s\n", key)
	//}
}
