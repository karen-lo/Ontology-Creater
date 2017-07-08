#!/usr/bin/python
# Calculate ranking

import string
import sys
import re

from sys import argv

if(len(argv) != 3):
	print "Wrong usage for the script."
	print "Proper Usage: python RankingNpP1.py hashmap ranking_file" #hashmap2.txt text.txt
	sys.exit()

script, hashmap, outfile = argv

np_list = {}

with open(hashmap, 'r') as readfile:
	freq = 0

	for line in readfile:

		match_num = re.match('^[0-9]+ -', line)

		if match_num:
			freq = (int) (line.split(' ')[0])

		else:
			line = line.strip()
			line = line.lower()
			
			temp = re.sub('[^0-9a-zA-Z]+', '', line)

			if temp == '':
				continue

			np_list[line] = freq

ranking = {}

for phrase in np_list:

	char_len = 0
	temp_phrase = re.sub(' ', '', phrase)
	
	for char in temp_phrase:
		char_len = char_len + 1

	quad = [np_list[phrase], 0, 0, char_len]
	# occur = 0

	for p in np_list:

		if phrase == p:
			continue

		if phrase in p:
			# occur = occur + np_list[p]
			# print phrase + " in " + p + " occured: " + str(occur) + " times"
			quad[1] = quad[1] + np_list[p]
			quad[2] = quad[2] + 1

	ranking[phrase] = quad

	#print phrase + ": " + str(quad[0]) + ", " + str(quad[1]) + ", " + str(quad[2]) + ", " + str(quad[3])

with open(outfile, 'w') as writefile:
	for r in ranking:
		quad = ranking[r]
		writefile.write(r + ": " + str(quad[0]) + ", " + str(quad[1]) + ", " + str(quad[2]) + ", " + str(quad[3]) + "\n")