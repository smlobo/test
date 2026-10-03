package handler

import (
	"fmt"
	"log"
	"net/http"
	"time"
)

func FooHandler(w http.ResponseWriter, r *http.Request) {
	log.Println("Received /foo request from:", r.Host, r.URL.Path, "::", r.Method)
	w.WriteHeader(http.StatusOK)
	responseBody := fmt.Sprintf("From /foo: %s", time.Now().Local().Format("2006-01-02_15-04-05.000-0700"))
	w.Write([]byte(responseBody))
}


