package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
)

type DomainToken struct {
	Domain, Token string
}

var envTable = map[string]DomainToken{
	"pendo-io": {
		"https://jobs-large-dot-pendo-io.gke.us.pendo.io",
		"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6Imdvb2dsZSIsImV4cCI6MTY2NDU1NDczMiwiZ3JhbnRUaW1lIjoiMTcxNzg5YTVkMGQ2ZjBlNiIsImlhdCI6MTY2Mzk0OTkzMiwibmJmIjoxNjYzOTQ5OTMyLCJzZXNzaW9uRXBvY2giOiIxNzE2OGNlOTAyN2EwMzA2Iiwic2Vzc2lvbmlkIjoiMjA3ODU1MzM0N2ZkODcyIiwidXNlcmlkIjoiNDcxMDEyNzA1MzQzODk3NiIsInZlbmRvcklkIjoiYjQ3OWQyY2QtODM5OC00NTg2LThkNzItMmM1NDJhNTYwMWYzIiwidmVuZG9yVXNlcklkIjoiNWRhZjBiNWEtMWU2OS00OGIwLTk3NmYtMTNhYTA1NGJmZjQxIn0.UPFBRkZ3KCXk1WvxZRShrRE49agSqTTpI_Zm_H5e9gU",
	},
	"pendo-eu": {
		"https://jobs-large-dot-pendo-eu.gke.eu.pendo.io",
		"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6Imdvb2dsZSIsImV4cCI6MTY2NDgxMzUyMSwiZ3JhbnRUaW1lIjoiMTcxODc1MDNiYjljMjdmYiIsImlhdCI6MTY2NDIwODcyMSwibmJmIjoxNjY0MjA4NzIxLCJzZXNzaW9uRXBvY2giOiIxNmZjOGIwNjJlZTUyNWQ1Iiwic2Vzc2lvbmlkIjoiMzIwNGNlMDVmOTc1MDNlZCIsInVzZXJpZCI6IjUyODI4NTkwMDI2MjYwNDgifQ.fVQAdXevmLLfnwlPwBu2EejgIFYsIwEapx21OUYpqBs",
	},
	"pendo-us1": {
		"https://jobs-large-dot-pendo-us1.gke.us1.pendo.io",
		"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6Imdvb2dsZSIsImV4cCI6MTY2NDgxMzYyOSwiZ3JhbnRUaW1lIjoiMTcxODc1MWNkODM5NGM0YSIsImlhdCI6MTY2NDIwODgyOSwibmJmIjoxNjY0MjA4ODI5LCJzZXNzaW9uRXBvY2giOiIxNmZmNDk3YzRkOTY0MWI2Iiwic2Vzc2lvbmlkIjoiNDUwZThmZTYyNTllZDIyOCIsInVzZXJpZCI6IjYwMTg3MTU2NTI5ODA3MzYiLCJ2ZW5kb3JJZCI6IjZkNzMwYTlmLTAyZjEtNGU0OS05NTUwLWZhMGFiMjNkMjQzZCIsInZlbmRvclVzZXJJZCI6IjdhNjYzY2M1LTI0NmUtNGNkOC05MTkwLTY0MGNkMThjNTI2MiJ9.LXMHTatZaBTpTdvPMN99fHcn9ipfu_2vh1nGKC7v3rY",
	},
	"pendo-ionchef": {
		"https://jobs-large-dot-pendo-ionchef.gke.pendo-dev.com",
		"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRoVHlwZSI6Imdvb2dsZSIsImV4cCI6MTY2NDgyMjE1NywiZ3JhbnRUaW1lIjoiMTcxODdjZGU3YjZkMjA2YiIsImlhdCI6MTY2NDIxNzM1NywibmJmIjoxNjY0MjE3MzU3LCJzZXNzaW9uRXBvY2giOiIxNzBmNDBiYTBjMzk5OWY4Iiwic2Vzc2lvbmlkIjoiNjZlYTI5YTc2ZGYyZmMwNyIsInVzZXJpZCI6IjYyNjc1MjMzNjAwOTYyNTYifQ._dF3YtNmeQiOT0gmriXvVZHvb70bwj2FrsMakTOBxis",
	},
	"pendo-test": {
		"https://jobs-large-dot-pendo-test.gke.pendo-test.pendo-dev.com",
		"",
	},
}

func doGet(endpoint string) []byte {
	fullURL := envTable[environment].Domain + endpoint

	request, err := http.NewRequest("GET", fullURL, nil)
	if err != nil {
		log.Fatalf("Error creating request %s: %s", fullURL, err)
	}
	request.Header.Add("Cookie", fmt.Sprintf("pendo.sess.jwt=%s", envTable[environment].Token))
	//cookie := &http.Cookie{
	//	Name:   "pendo.sess.jwt",
	//	Value:  envTable[environment].Token,
	//	MaxAge: 300,
	//}
	//request.AddCookie(cookie)
	//log.Printf("req: %s", request)

	client := &http.Client{}
	response, err := client.Do(request)
	if err != nil {
		log.Fatalf("Error when GET on %s: %s", fullURL, err)
	}
	if response.StatusCode >= 400 {
		log.Fatalf("Response error when GET on %s: %d", fullURL, response.StatusCode)
	}

	responseData, err := ioutil.ReadAll(response.Body)
	if err != nil {
		log.Fatalf("Error reading response body for %s: %s", fullURL, err)
	}
	return responseData
}

func testHelloRequest() {
	log.Printf("Hello: %s -> %s", environment, string(doGet("/")))
}

func testGuide() {
	log.Printf("Guide JSON (%s):", environment)
	fmt.Println(string(doGet("/api/super/ss/Revinate_Test_To/guide")))
}
