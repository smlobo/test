#!/usr/bin/python3

import sys
import urllib.request

if len(sys.argv) != 2:
	print("regex.py <server:port/path>")
	quit()

# Add http:// to make compatible with HttpSocket.java & http-socket.py

fhand = urllib.request.urlopen("http://" + sys.argv[1])
for line in fhand:
    print(line.decode().strip())
