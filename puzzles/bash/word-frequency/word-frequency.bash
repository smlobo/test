#!/usr/bin/bash

# Write a bash script to calculate the frequency of each word in a 
# text file words.txt.
#
# For simplicity sake, you may assume:
#
# words.txt contains only lowercase characters and space ' ' 
# characters.
# Each word must consist of lowercase characters only.
# Words are separated by one or more whitespace characters.
# Example:
#
# Assume that words.txt has the following content:
#
# the day is sunny the the
# the sunny is is
# Your script should output the following, sorted by descending 
# frequency:
# 
# the 4
# is 3
# sunny 2
# day 1

# Associative array
#declare -a warray
# Associative integer array
declare -Ai warray

while read line; do
    for word in $line; do
        #echo "word = '$word'"
        if [ ${warray[$word]} ]
        then
        	let warray[$word]++
        	#warray[$word]+=1
        else
        	warray[$word]=1
        fi
    done
done < $1

#echo "${!warray[@]}"

for key in "${!warray[@]}"
do
	echo $key ${warray[$key]}
done | 
# Sort reverse, numeric with 2nd field as the sort key
sort -rn -k2
