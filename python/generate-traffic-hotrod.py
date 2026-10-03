#!/usr/bin/python3

import requests
import random
import time

urls = [
	"http://localhost:8080/dispatch?customer=392", 
	"http://localhost:8080/dispatch?customer=731", 
	"http://localhost:8080/dispatch?customer=567", 
	"http://localhost:8080/dispatch?customer=123", 
	"http://localhost:8080/dispatch?customer=000" ]

countRequests = 0
countFail = 0
printInterval = 100
delay = 3

# Every Xth iteration MAY have a bad request
badRequest = 100

while True:
	# 4 requests at a time (only 1 could be bad)
	# Random url
	if countRequests%badRequest == 0:
		url1 = urls[random.randrange(0, len(urls))]
	else:
		url1 = urls[random.randrange(0, len(urls)-1)]
	url2 = urls[random.randrange(0, len(urls)-1)]
	url3 = urls[random.randrange(0, len(urls)-1)]
	url4 = urls[random.randrange(0, len(urls)-1)]

	# print("{}".format(url))
	response = requests.get(url1)
	if response.status_code != 200:
		countFail += 1
	# print("  -> {}".format(response.status_code))
	# print("  -> {}".format(response.text))

	response = requests.get(url2)
	response = requests.get(url3)
	response = requests.get(url4)

	countRequests += 1

	if countRequests%printInterval == 0:
		print("Req: {}; Fail: {} <{}>".format(countRequests, countFail, url1))
	time.sleep(delay)
