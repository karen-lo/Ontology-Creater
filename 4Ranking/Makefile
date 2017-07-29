# This is a rudimentary Makefile for rebuilding Stanford CoreNLP.
# We actually use ant (q.v.) or a Java IDE.

JAVAC = javac
JAVAFLAGS = -O -d classes -encoding utf-8 -cp "*"

start:
	javac CombineHashMaps.java
	javac RankingNp.java

nounsearch:
	cd nlp/stanford-corenlp-full-2016-10-31
	make nounsearch

hashmap:
	java CombineHashMaps output1.txt output2.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output3.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output4.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output5.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output6.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output7.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output8.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output9.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output10.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output11.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output12.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output13.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output14.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output15.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output16.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output17.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output18.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output19.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output20.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output21.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output22.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output23.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output24.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output25.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output26.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output27.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output28.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output29.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output30.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output31.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output32.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output33.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output34.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output35.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output36.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output37.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output38.txt hashmap2.txt
	java CombineHashMaps hashmap2.txt output39.txt hashmap3.txt
	java CombineHashMaps hashmap3.txt output40.txt hashmap4.txt
	java CombineHashMaps hashmap4.txt output41.txt hashmap5.txt
	java CombineHashMaps hashmap5.txt output42.txt hashmap1.txt
	java CombineHashMaps hashmap1.txt output43.txt overall_hashmap.txt

ranking:
	java RankingNp overall_hashmap.txt ranking.txt termhood.txt




# Before making, unjar the source jar file in the 'src' directory
source:
	if [ ! -e src ] ; then \
	  mkdir src ; cd src ; jar -xf ../stanford-corenlp-*-sources.jar; \
	fi;

clean:
	rm hashmap*
	rm output*
