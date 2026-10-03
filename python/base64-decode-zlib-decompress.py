#!/usr/bin/python3

import zlib
import sys
import base64

if len(sys.argv) != 2:
    print("Usage: zlib-decompress.py <string>")
    quit()

print(zlib.decompress(base64.b64decode(sys.argv[1])))
