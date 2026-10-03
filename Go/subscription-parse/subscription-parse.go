package main

import (
	"bytes"
	"encoding/json"
	"io"
	"io/ioutil"
	"log"
	"os"
)

type Subscription struct {
	Id                            string
	Name                          string
	DisplayName                   string
	TrainingPartnerSubscriptionId int64
	Applications                  []Application
}

type Application struct {
	Id          int64
	Name        string
	DisplayName string
}

var subs = make(map[string]Subscription)

func decodeJsonStream(jsonStream []byte) {
	dec := json.NewDecoder(bytes.NewReader(jsonStream))
	for {
		var sub Subscription
		if err := dec.Decode(&sub); err == io.EOF {
			break
		} else if err != nil {
			log.Fatal(err)
		}
		//log.Printf("  %s: %s\n", sub.Name, sub.Id)
		subs[sub.Id] = sub
	}
}

func main() {
	// Usage
	if len(os.Args) < 2 || len(os.Args) > 3 {
		log.Fatalf("Usage: %s <subscription-json-array> <log-file>\n", os.Args[0])
	}

	// Set logfile output
	if len(os.Args) == 3 {
		logFile, err := os.OpenFile(os.Args[2], os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
		if err != nil {
			log.Fatalf("error opening log file %s: %v", os.Args[2], err)
		}
		defer logFile.Close()
		log.SetOutput(logFile)
	}

	// Input JSON being parsed
	content, err := ioutil.ReadFile(os.Args[1])
	if err != nil {
		log.Fatalf("Error when opening file %s: %v", os.Args[1], err)
	}

	//var subs []map[string]interface{}
	//_ = json.Unmarshal(content, &subs)

	decodeJsonStream(content)
	log.Printf("# of subs: %d", len(subs))

	setEnvironment(os.Args[1])

	// Test
	//testHelloRequest()
	//testGuide()

	// 09/28/2022 parent subs with aeu subs who have guides public
	parentSubsAEUSubsPublic()
}

func parentSubsAEUSubsPublic() {
	// Subs with trainingPartnerSubscriptionId
	aeuSubs := make([]Subscription, 0)
	for _, sub := range subs {
		if sub.TrainingPartnerSubscriptionId == 0 {
			continue
		}
		aeuSubs = append(aeuSubs, sub)
	}
	log.Printf("# of AEU subs: %d", len(aeuSubs))

	// aeu subs with public Guides
	for _, aeuSub := range aeuSubs {
		// Get Guides
		guideEndpoint := "/api/ss/" + aeuSub.Name + "/guide"
		guideData := doGet(guideEndpoint)
		var guideSlice []Guide
		_ = json.Unmarshal(guideData, &guideSlice)
		//if len(guideSlice) > 0 {
		log.Printf("  aeu sub: %s; # guides: %d", aeuSub.Name, len(guideSlice))
		//}
		for index, guide := range guideSlice {
			if guide.State == "public" {
				log.Printf("[%d] public guide: %s", index, guide.Name)
				//} else {
				//	log.Printf("[%d] inactive guide: %s (%s)", index, guide.Name, guide.State)
			}
		}
	}

}
