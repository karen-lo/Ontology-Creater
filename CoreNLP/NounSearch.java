package CoreNLP;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.stanford.nlp.coref.CorefCoreAnnotations;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;
import edu.stanford.nlp.process.*;

public class NounSearch {

    /** Usage: java -cp ".:*" NounSearch inputFile stoplist firstWordStopList [outputFile] [outputHashMap] **/
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

    /* Set up optional output files */
        PrintWriter out;
        PrintWriter hashmap;
        if(args.length > 1) {
            out = new PrintWriter(args[3]);
            hashmap = new PrintWriter(args[4]);
        } else {
            out = new PrintWriter(System.out);
            hashmap = new PrintWriter(System.out);
        }

        PrintWriter xmlOut = null;
        if (args.length > 2) {
            xmlOut = new PrintWriter(args[4]);
        }

    /* Properties to annotate for */
        Properties property = new Properties();
        property.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(property);

    /* Text to annotate */
        Annotation file;
        if (args.length > 0) {
            file = new Annotation(IOUtils.slurpFileNoExceptions(args[0]));
        } else {

            // For testing
            //file = new Annotation("There was a silvery woman who went to Peterson Church and took a wet pail to grab some water for her father.");
            file = new Annotation("the number of genera and species of butterfly fishes on each study");
        }

    /* Extract stoplists*/
        ArrayList<String> stopList = new ArrayList<String>();
        ArrayList<String> firstWordStopList = new ArrayList<String>();

        BufferedReader br = null;
        FileReader fr = null;

        Stemmer s = new Stemmer();

        // Stoplist
        try {
            fr = new FileReader(args[1]);
            br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null) {
                line = line.trim();
                line = line.toLowerCase();
                line = s.stem(line);
                if(stopList.contains(line) == false) stopList.add(line);
            }

        } catch(IOException e) {
            e.printStackTrace();

        } finally {
            if(br != null) br.close();

            if(fr != null) fr.close();
        }

        // First word stoplist
        try {
            fr = new FileReader(args[2]);
            br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null) {
                line = line.trim();
                if(firstWordStopList.contains(line) == false) firstWordStopList.add(line);
            }

        } catch(IOException e) {
            e.printStackTrace();

        } finally {
            if(br != null) br.close();

            if(fr != null) fr.close();
        }

        // Run all the selected Annotators on this text and print it out
        pipeline.annotate(file);
        //pipeline.prettyPrint(file, out);

        // Data structures
        List<CoreMap> sentences = file.get(CoreAnnotations.SentencesAnnotation.class); //get sentences
        HashMap<String, Integer> NPList = new HashMap<String, Integer>();

        if(sentences != null && !sentences.isEmpty()) {

            for(CoreMap sentence : sentences) {
                ArrayList<Tree> NPNodes = new ArrayList<Tree>();

                // For each sentence, get its parse tree
                Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

                // Get each node in the tree
                List<Tree> subtrees = tree.subTreeList();

                // Grab noun phrase nodes
                for(Tree subtree : subtrees) {

                    if(subtree.label().value().equals("NP")) {
                        NPNodes.add(subtree);
                    }
                }

        /* Extract noun phrase strings */
                for(Tree np : NPNodes) {
                    String nounphrase = "";
                    List<Tree> nodes = np.getLeaves();
                    int count = 0;

                    for(Tree child : nodes) {
                        if(child.ancestor(1, np) == null) {
                            //do nothing
                        } else if(count == 0 && (child.ancestor(1, np)).label().value().equals("DT")) { // Remove articles
                            count++;
                            continue;
                        }

                        // first word is in stoplist, skip word
                        if(count == 0 && firstWordStopList.contains(child.label().value()) == true) {
                            count++;
                            continue;
                        }

                        if(!nounphrase.equals("")) {
                            nounphrase = nounphrase + " " + child.label().value();
                        } else {
                            nounphrase = child.label().value();
                        }
                    }

                    nounphrase = nounphrase.trim();

          /* Check for unwanted punctuations */
                    ArrayList<Integer> charToStrip = new ArrayList<Integer>();
                    int offset = 0;

                    for(int i=0; i<nounphrase.length(); i++) {
                        char c = nounphrase.charAt(i);
                        boolean lastChar = (i+1 == nounphrase.length());

                        if(c == '-') {
                            // Hanging hypens
                            if(i == 0 || lastChar || nounphrase.charAt(i-1) == ' ' || nounphrase.charAt(i+1) == ' ') {
                                if(lastChar) {
                                    nounphrase = nounphrase.substring(0, i);
                                } else if(i == 0) {
                                    nounphrase = nounphrase.substring(i+1);
                                } else {
                                    nounphrase = nounphrase.substring(0, i) + nounphrase.substring(i+1);
                                }
                            }
                        }

                        if(c == '\'') {
                            boolean singleQuote = (i == 0 || nounphrase.charAt(i-1) == ' ') &&
                                    (lastChar || (nounphrase.charAt(i+1) == ' '));
                            //boolean singularApostrophe = (nounphrase.charAt(i+1) == 's') || (nounphrase.charAt(i+1) == 'S');
                            //boolean pluralApostrophe = (nounphrase.charAt(i-1) == ' ') &&
                            //        (nounphrase.charAt(i-2) == 's' || nounphrase.charAt(i-2) == 'S') && (nounphrase.charAt(i+1) == ' ')
                            if(singleQuote) {
                                if(lastChar) {
                                    nounphrase = nounphrase.substring(0, i);
                                } else if(i == 0) {
                                    nounphrase = nounphrase.substring(i+1);
                                } else {
                                    nounphrase = nounphrase.substring(0, i) + nounphrase.substring(i+1);
                                }
                            }
                        }

                        if(c == ',') {
                            if(lastChar) {
                                nounphrase = nounphrase.substring(0, i);
                            } else if(i == 0) {
                                nounphrase = nounphrase.substring(i+1);
                            } else {
                                nounphrase = nounphrase.substring(0, i) + nounphrase.substring(i+1);
                            }
                        }
                    }

          /* Add to hashmap */
                    boolean hasNumber = true;
                    hasNumber = nounphrase.matches(".*\\d+.*");

                    boolean inStopList = true;
                    inStopList = stopList.contains(s.stem(nounphrase.toLowerCase()));

           /* Check for multiple stoplist words in conjunction clause */
                    if(!inStopList){
                        boolean and = false;
                        String[] spaceSplit = nounphrase.split("\\s+");
                        String str = "";

                        for(int i=0; i<spaceSplit.length; i++) {
                            if(spaceSplit[i].equals("and")) {
                                and = true;
                            }

                            if(and) {
                                inStopList = stopList.contains(s.stem(str.toLowerCase()));
                                str = "";
                                and = false;
                            } else if(i == spaceSplit.length-1) {
                                str = str + " " + spaceSplit[i];
                                inStopList = stopList.contains(s.stem(str.toLowerCase()));
                            } else {
                                str = str + " " + spaceSplit[i];
                            }
                        }
                    }

                    if(!hasNumber && !inStopList && nounphrase != "") { // no numbers
                        if(NPList.containsKey(nounphrase)) {
                            NPList.put(nounphrase, ((int) NPList.get(nounphrase)) + 1);

                        } else {
                            NPList.put(nounphrase, 1);
                        }
                    }
                }

            }
        }

    /* Print noun phrases in order of frequency */
        System.out.println("printing");
        Map<Integer, ArrayList<String>> orderedMap = new TreeMap<Integer, ArrayList<String>>();

        Pattern alpha = Pattern.compile("^[a-zA-Z]"); // Regex for alphabet

        for(Map.Entry<String, Integer> entry : NPList.entrySet()) {

      /* Check to see if size is 2 or less*/
            if(entry.getKey().length() <= 2) continue;

      /* Check to see that first char is a part of alphabet */
            Matcher m = alpha.matcher(entry.getKey());
            if(!m.find()) continue;

            hashmap.println(entry.getKey() + " : " + entry.getValue());

            if(orderedMap.containsKey(entry.getValue())) {
                ArrayList<String> temp = orderedMap.get(entry.getValue());
                temp.add(entry.getKey());
                orderedMap.put(entry.getValue(), temp);
            } else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(entry.getKey());
                orderedMap.put(entry.getValue(), temp);
            }
        }

        for(Map.Entry<Integer, ArrayList<String>> entry : orderedMap.entrySet()) {
            out.println(entry.getKey() + " - ");
            ArrayList<String> temp = entry.getValue();
            for(int i=0; i<temp.size(); i++) {
                out.println("\t" + temp.get(i));
            }
        }

        out.close();
        hashmap.close();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
