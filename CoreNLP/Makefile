# This is a rudimentary Makefile for rebuilding Stanford CoreNLP.
# We actually use ant (q.v.) or a Java IDE.

JAVAC = javac
JAVAFLAGS = -O -d classes -encoding utf-8 -cp "*"

# Builds the classes' jar file
corenlp: source
	mkdir -p classes
	$(JAVAC) $(JAVAFLAGS) src/edu/stanford/nlp/*/*.java \
	    src/edu/stanford/nlp/*/*/*.java \
	    src/edu/stanford/nlp/*/*/*/*.java \
	    src/edu/stanford/nlp/*/*/*/*/*.java \
	    src/edu/stanford/nlp/*/*/*/*/*/*.java
	cd classes ; jar -cfm ../stanford-corenlp-`date +%Y-%m-%d`.jar ../src/META-INF/MANIFEST.MF edu ; cd ..

nounsearch:
	java -cp ".:*" NounSearch ../../1.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output1.txt ../../hashmap1.txt
	java -cp ".:*" NounSearch ../../2.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output2.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../3.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output3.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../4.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output4.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../5.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output5.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../6.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output6.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../7.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output7.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../8.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output8.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../9.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output9.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../10.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output10.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../11.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output11.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../12.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output12.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../13.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output13.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../14.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output14.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../15.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output15.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../16.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output16.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../17.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output17.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../18.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output18.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../19.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output19.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../20.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output20.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../21.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output21.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../22.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output22.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../23.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output23.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../24.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output24.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../25.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output25.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../26.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output26.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../27.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output27.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../28.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output28.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../29.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output29.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../30.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output30.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../31.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output31.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../32.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output32.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../33.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output33.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../34.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output34.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../35.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output35.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../36.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output36.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../37.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output37.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../38.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output38.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../39.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output39.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../40.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output40.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../41.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output41.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../42.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output42.txt ../../hashmap1.txt 
	java -cp ".:*" NounSearch ../../43.txt ../../stoplist.txt ../../firstWordStopList.txt ../../output43.txt ../../hashmap1.txt 

# Before making, unjar the source jar file in the 'src' directory
source:
	if [ ! -e src ] ; then \
	  mkdir src ; cd src ; jar -xf ../stanford-corenlp-*-sources.jar; \
	fi;

clean:
	rm -rf classes
	rm -rf src
