#!/usr/bin/python
# Common Word Finder Script

import string
import sys
import os

from sys import argv

if(len(argv) != 2):
	print "Wrong usage for the script."
	print "Proper Usage: python runNLP.py [number of docs]"
	sys.exit()

script, total = argv

for i in range(int(total)):
	readfile = str(i+1) + ".txt"
	os.system('java -cp ".:*" NounSearch ../../' + readfile + ' ../../output' + str(i+1) + '.txt')
	
