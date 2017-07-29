#!/usr/bin/python
# Common Word Finder Script

import string
import sys
import re

from sys import argv

if(len(argv) != 2):
	print "Wrong usage for the script."
	print "Proper Usage: python abstractSplitter.py [infile]"
	sys.exit()

script, infile = argv
count = 0
split = 1

# Read Input
with open(infile, 'r') as readfile:
	writefile = open(str(split) + ".txt", "w")

	for line in readfile:
		count = count + 1

		if count >= 1000:
			count = 0
			split = split + 1
			writefile.close()
			writefile = open(str(split) + ".txt", "w")
		
		writefile.write(line)

readfile.close()
writefile.close()
print "Files is closed: " + str(readfile.closed) + ", " + str(writefile.closed)




