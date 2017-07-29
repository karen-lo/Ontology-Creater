# Ontology-Creator

### Step One: Clean and Split Input
1. Place input `.txt` file into `/Input` directory
2. Run the Python scripts

### Step Two: CoreNLP - Discovering Noun Phrases
1. Run `NounSearch.java` on the output files
2. It will create hashmap files

### Step Three: Ranking Noun Phrases
1. Run the Makefile in Ranking
```sh
$ make hashmap
$ make ranking
```
2. Output: ranking.txt
	- [*noun phrase*]: [*total frequency in corpus*], [*frequency as a substring*], [*number of longer strings*], [*length of string*]
3. Output: termhood.txt
	- [*termhood*] - string count: [*number of occurrences*]
		[*list of the noun phrases with this string count*]
        [*...*]

### Step Four: SciGraph API - Existing Noun Phrase
1. Run `JaccardDeterminer.java`
2. Output: [*name of dataset*]\_in\_ontology.tsv
	- [*term*]	[*Jaccard value*]	[*Cinergi term*]
	If Jaccard value = 1.0, then there is an exact match in Cinergi
3. Output: [*name of dataset*]\_unique.tsv
	- [*term*]	[*Jaccard value*]	[*closest Cinergi term*]
