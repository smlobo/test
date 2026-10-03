#!/usr/bin/python3

import requests
import random
import time

urls = [
	"http://localhost:8180/api/vet/vets", 
	"http://localhost:8180/api/gateway/owners/1", 
	"http://localhost:8180/api/gateway/owners/2", 
	"http://localhost:8180/api/gateway/owners/3", 
	"http://localhost:8180/api/gateway/owners/4", 
	"http://localhost:8180/api/gateway/owners/5", 
	"http://localhost:8180/api/gateway/owners/6", 
	"http://localhost:8180/api/gateway/owners/7", 
	"http://localhost:8180/api/gateway/owners/8", 
	"http://localhost:8180/api/gateway/owners/9", 
	"http://localhost:8180/api/gateway/owners/10" ]

countRequests = 0
countFail = 0
printInterval = 100
delay = 2

while True:
	# Random url
	url = urls[random.randrange(0, len(urls))]

	# print("{}".format(url))
	response = requests.get(url)
	countRequests += 1
	if response.status_code != 200:
		countFail += 1
	# print("  -> {}".format(response.status_code))
	# print("  -> {}".format(response.text))


	if countRequests%printInterval == 0:
		print("Req: {}; Fail: {} <{}>".format(countRequests, countFail, url))
	time.sleep(delay)
