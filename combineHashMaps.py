#!/usr/bin/python
# Script to combine hashmaps

import string
import sys
import re

from sys import argv

if(len(argv) != 4):
	print "Wrong usage for the script."
	print "Proper Usage: python combineHashMaps.py HashMap1 HashMap2 outputfile"
	sys.exit()

script, hashmap1, hashmap2, outfile = argv

npList = {}

with open(hashmap1, 'r') as readfile:
	freq = 0

	for line in readfile:

		matchNum = re.match('^[0-9]+ -', line)

		if matchNum:
			freq = (int) (line.split(' ')[0])

		else:
			line = line.strip()
			line = line.lower()
			
			temp = re.sub('[^0-9a-zA-Z]+', '', line)

			if temp == '':
				continue

			npList[line] = freq

with open(hashmap2, 'r') as readfile:
	freq = 0

	for line in readfile:

		matchNum = re.match('^[0-9]+ -', line)

		if matchNum:
			freq = (int) (line.split(' ')[0])

		else:
			line = line.strip()
			line = line.lower()
			
			temp = re.sub('[^0-9a-zA-Z]+', '', line)

			if temp == '':
				continue

			if line in npList:
				npList[line] = npList[line] + freq

			else:
				npList[line] = freq


freqList = {}

for key in npList:

	if npList[key] in freqList:
		tempList = freqList[npList[key]]
		tempList.append(key)


	else:
		tempList = []
		tempList.append(key)
		freqList[npList[key]] = tempList


with open(outfile, 'w') as writefile:
	for key in freqList:
		writefile.write(str(key) + " - \n")

		for phrase in freqList[key]:
			writefile.write('\t' + phrase + '\n')