#!/usr/bin/python3

import sys
import socket

if len(sys.argv) != 2:
    print("regex.py <server:port/path>")
    quit()

temp = sys.argv[1].split(":")
server = temp[0]

temp = temp[1].split("/")
port = int(temp[0])

path = temp[1]

print("Connecting to: server={}, port={}, path={}".format(server, port, path))

mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
mysock.connect((server, port))
# scmd = "GET http://{}:{}/{} HTTP/1.0\r\n\r\n".format(server, port, path)
scmd = "GET http://{}:{}/{} HTTP/1.0\r\n".format(server, port, path)
scmd += "Host: {}\r\n".format(server)
scmd += "User-Agent: Sheldon\r\n"
scmd += "Accept-Language: en-us\r\n"
scmd += "\r\n"
cmd = scmd.encode()
mysock.send(cmd)

while True:
    data = mysock.recv(512)
    if (len(data) < 1):
        break
    print(data.decode(), end='')

mysock.close()
