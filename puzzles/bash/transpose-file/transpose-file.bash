#!/usr/bin/bash

# Given a text file file.txt, transpose its content.
#
# You may assume that each row has the same number of columns and each 
# field is separated by the ' ' character.
#
# Example:
#
# If file.txt has the following content:
# name age
# alice 21
# ryan 30
#
# Output the following:
# name alice ryan
# age 21 30

declare -a sarray

while read line
do
	#count=0
	declare -i count=0
	for word in $line
	do
		echo $count - $word
		sarray[$count]+=$word
		sarray[$count]+=' '
		#let count+=1
		count+=1
	done
done < $1

#echo "${sarray[@]}"

for i in "${sarray[@]}"
do
	echo $i
done
