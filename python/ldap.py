#!/usr/bin/python3

import os
import sys
import re
import datetime

# Old data
oldlist = list()
fhandle = open("/home/smlobo/test/Python/rifManyOU.list")
for old in fhandle.readlines():
	name = re.match(r'[\d-]+, (\w*),', old)
	words = old.split()
	#print(name.group(1))
	oldlist.append(name.group(1))
fhandle.close()

costCenterList = ["BC78", "0652", "CT44", "OU30", "M0P1", "2JD1", "8BP1", "AV24", "6DD1", "0615", "K0P1", "CR56", "CR59", "CR83", "PL07", "DV07", "PL20", "K0P1", "15P1", "FXB1"]

costCenterString = ""
for costCenter in costCenterList:
	costCenterString += "(orclcorpcostcenter={})".format(costCenter)
#print(costCenterString)
ldapString = "ldapsearch -x -H ldaps://ldap.oracle.com -b \"dc=oracle,dc=com\" \"(&(|{})(orclbeehiveuserstatus=false))\" city ou c mail".format(costCenterString)
#print(ldapstring)

results = os.popen(ldapString)

#employees = list()

fhandle = open("/home/smlobo/test/Python/rifManyOU.list", 'a')
lines = results.readlines()
for index in range(len(lines)):
	#print(lines[index])
	match = re.match(r'dn: cn=(\w*)\,', lines[index])
	if not match:
		continue

	#print(match.group(1))
	#employees.append(match.group(1))

	if match.group(1) in oldlist:
		print("Found: {} {}".format(datetime.date.today(), match.group(1)))
		continue

	# read next (city?) line
	index += 1
	#print(lines[index])
	citymatch = re.match(r'city: ([\w\* ]*)', lines[index])
	cityname = ""
	if citymatch:
		cityname = citymatch.group(1)
		index += 1

	# read the org unit line
	oumatch = re.match(r'ou: ([\w ]*)', lines[index])
	orgunit = ""
	if oumatch:
		orgunit = oumatch.group(1)
		index += 1

	# read the country line
	countrymatch = re.match(r'c: (\w*)', lines[index])
	countryname = ""
	if countrymatch:
		countryname = countrymatch.group(1)
		index += 1

	# read the mail line
	mailmatch = re.match(r'mail: ([\w\.@]*)', lines[index])
	email = ""
	if mailmatch:
		email = mailmatch.group(1)
		index += 1

	print("Adding: {}, {}, {}, {}, {}, {}".format(datetime.date.today(), match.group(1), cityname, countryname, orgunit, email))

	fhandle.write("{}, {}, {}, {}, {}, {}\n".format(datetime.date.today(), match.group(1), cityname, countryname, orgunit, email))

fhandle.close()

print("Heartbeat: {}".format(datetime.date.today()))

#print(sorted(employees))
#print(datetime.date.today())

