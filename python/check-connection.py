#!/usr/bin/python3

'''
import urllib2

def internet_on():
	try:
		urllib2.urlopen('ldaps://ldap.oracle.com', timeout=1)
		return True
	except urllib2.URLError as err: 
		return False

if internet_on():
	print("Can see ldaps://ldap.oracle.com")
else:
	print("No connection")


import requests

def connected_to_internet(url='ldaps://ldap.oracle.com/', timeout=5):
    try:
        _ = requests.get(url, timeout=timeout)
        return True
    except requests.ConnectionError:
        print("No internet connection available.")
    return False

print(connected_to_internet())
'''

import os

response = os.system('ping -c 1 ldap.oracle.com')
if response == 0:
	print("ldap.oracle.com is up")
else:
	print("ldap.oracle.com is down")

