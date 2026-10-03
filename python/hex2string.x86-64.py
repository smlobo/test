#!/usr/bin/python3

import sys

if len(sys.argv) != 2:
    print("Usage: {} <hex-file>".format(__file__))
hexFile = sys.argv[1]

try:
    fhandle = open(hexFile)
except:
    print('File {} cannot be opened.'.format(hexFile))
    quit()

for line in fhandle:
    # Ignore non-address lines
    if not line.startswith("0000"):
        continue


    print(line, end='')
    lineList = line.split()
    print('                  ', end='')
    for i in range(len(lineList)):
        if i == 0:
            continue
        print('{} '.format(chr(int(lineList[i], 16))), end='')
    print()

fhandle.close()
