#!/usr/bin/python
# Common Word Finder Script

import string
import sys
import re

from sys import argv

if(len(argv) != 3):
	print "Wrong usage for the script."
	print "Proper Usage: python abstractCleaner.py [infile] [outfile]"
	sys.exit()

script, infile, outfile = argv
count = 0
split = 1

# Read Input
with open(infile, 'r') as readfile:
	with open(outfile, 'w') as writefile:
	# writefile = open(str(split) + ".txt", "w")

		for line in readfile:
			count = count + 1

			if line == "AUTHOR INDEX\n":
				break

			# if count >= 1000:
			# 	count = 0
			# 	split = split + 1
			# 	writefile.close()
			# 	writefile = open(split + ".txt", "w")

			if "@" in line:
				continue

			name = re.search('[A-Z]\.', line)
			if name:
				continue
			
			badsentence = re.search('\.[A-Z]+', line)
			if badsentence:
				print count
				# templine = line
				# i = 0
				# indexlist = []
				# while "." in templine:
				# 	indexlist.append(templine.index(".") + i)
				# 	templine = templine[:templine.index(".")] + templine[templine.index(".")+1]
				# 	i = i + 1

				# for j in indexlist:
				# 	line = line[:j+1] + " " + line[j+1:]

			email = re.search('\.[a-zA-Z]+', line)
			if email:
				continue



			# line = line.rstrip() + " "
			
			writefile.write(line)
		
		


readfile.close()
writefile.close()
print "Files is closed: " + str(readfile.closed) + ", " + str(writefile.closed)




