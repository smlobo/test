package handler

import (
	"bytes"
	"log"
	"net/http"
)

func HomeHandlerFactory(client *http.Client) func(http.ResponseWriter, *http.Request) {
	return func(w http.ResponseWriter, r *http.Request) {
		log.Println("Received / request from:", r.Host, r.URL.Path, "::", r.Method)

		body := bytes.NewBufferString("")
		res, err := client.Post("http://example.com", "application/json", body)
		if err != nil {
			log.Println("\tPOST to example.com response:", res, ", error:", err)
			w.WriteHeader(http.StatusInternalServerError)
			return
		}

		if res.StatusCode > 399 {
			log.Println("\tPOST to example.com response:", res)
			w.WriteHeader(http.StatusInternalServerError)
			return
		}

		log.Println("\tPOST to example.com good response:", res)
		w.WriteHeader(http.StatusOK)
	}
}
