#!/usr/bin/bash

# Print the 10th line of a file

tail -n+10 $1 | head -1
head -10 $1 | tail -1
