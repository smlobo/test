#!/usr/bin/python3

import requests
import random
import time

urls = [
	"http://192.168.214.3:8080/MySqlBookListApp/BookListQuery", 
	"http://192.168.214.3:8080/AsyncMySqlBookListApp/BookListQuery", 
	"http://192.168.214.3:8080/HibernateMySqlBookListApp/BookListQuery", 
	"http://192.168.214.3:8080/DBCPMySqlBookListApp/BookListQuery", 
	"http://192.168.214.3:8082/BookListQuery", 
	"http://192.168.214.3:8888/CFMLMySqlBookList/BookListAccess.cfm", 
	"http://192.168.214.3:8500/CFMLMySqlBookList/BookListAccess.cfm"]

# for index in range(len(urls)):
# 	payload = {'book_list_id': random.randint(1, 145)}
# 	print("[{}] {} {}".format(index, urls[index], payload))
# 	response = requests.get(urls[index], params=payload)
# 	print("  -> {}".format(response.status_code))
# 	#print("  -> {}".format(response.text))

countRequests = 0
countFail = 0
printInterval = 10

while True:
	# Random url
	url = urls[random.randrange(0, len(urls))]
	# Random parameter
	payload = {'book_list_id': random.randint(1, 145)}
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
	time.sleep(1)
