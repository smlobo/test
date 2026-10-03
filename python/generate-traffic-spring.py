#!/usr/bin/python3

import requests
import random
import time

urls = [
	# "http://192.168.214.3:8086/movies", 
	# "http://192.168.214.3:8086/countries", 
	# "http://192.168.214.3:8087/movies", 
	# "http://192.168.214.3:8087/countries"
	"http://localhost:8086/movies", 
	"http://localhost:8086/countries", 
	"http://localhost:8086/movie", 
	"http://localhost:8086/country", 
	"http://localhost:8087/movies", 
	"http://localhost:8087/countries"
]

countRequests = 0
countFail = 0
printInterval = 50
delay = 2

while True:
	# Random url
	url = urls[random.randrange(0, len(urls))]
	# Random parameter
	payload = {'count': random.randint(1, 10)}
	# print("{} {}".format(url, payload))
	response = requests.get(url, params=payload)
	# print("  -> {}".format(response.status_code))
	# print("  -> {}".format(response.text))

	countRequests += 1
	if response.status_code != 200:
		countFail += 1
	if countRequests%printInterval == 0:
		print("Req: {}; Fail: {} <{}{}>".format(countRequests, countFail, 
			url, payload))
	time.sleep(delay)
