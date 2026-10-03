package main

import (
	"encoding/json"
	"fmt"
	"os"
    "io/ioutil"
    "io"
    "net/http"
)

func main() {
	unmarshalRqliteVersion := func(bytes []byte) {

		var mapData map[string]interface{}
		err := json.Unmarshal(bytes, &mapData)
		if err != nil {
			fmt.Println("error:", err)
		}
		// fmt.Printf("%+v\n", mapData)
		fmt.Printf("  rqlite map len: %d\n", len(mapData))

		// "build" info
		buildData, ok := mapData["build"].(map[string]interface{})
		if !ok {
			fmt.Printf("Could not type assert build info into a map[string]interface{}")
		}
		// fmt.Printf("'build' entry: %+v\n", buildData)

		// "version" info
		versionData, ok := buildData["version"].(string)
		if !ok {
			fmt.Printf("Could not type assert version info into a string")
		}
		fmt.Printf("  'version' entry: %s\n", versionData)
	}

	fmt.Printf("Unmarshal JSON file: %s\n", os.Args[1])
    iFile, _ := os.OpenFile(os.Args[1], os.O_RDONLY, os.ModePerm)
    fileBytes, _ := ioutil.ReadAll(iFile)
    iFile.Close()
    unmarshalRqliteVersion(fileBytes)

    rqliteUrl := "http://10.10.1.41:4001/status"
	fmt.Printf("Unmarshal URL: %s\n", rqliteUrl)
	res, _ := http.Get(rqliteUrl)
	netBytes, _ := io.ReadAll(res.Body)
	res.Body.Close()
	unmarshalRqliteVersion(netBytes)
}
