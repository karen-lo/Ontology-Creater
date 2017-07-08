import java.io.*;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Assign Ranking to Nounphrases in Hashmap
public class RankingNp {

  /** Usage: java RankingNp Hashmap outfile termfile **/
	public static void main(String args[]) throws IOException {
		long startTime = System.currentTimeMillis();

		PrintWriter out;
    PrintWriter termfile;
    if (args.length == 3) {
      out = new PrintWriter(args[1]);
      termfile = new PrintWriter(args[2]);
    } else {
      out = new PrintWriter(System.out);
      termfile = new PrintWriter(System.out);
      System.out.println("Wrong usage.");
      System.exit(0);
    }

    BufferedReader br = null;
    FileReader fr = null;

    HashMap<String, Integer> NPList = new HashMap<String, Integer>();

    /* Collect Hashmap */
    try {
      fr = new FileReader(args[0]);
      br = new BufferedReader(fr);

      String line;
      Pattern matchNum = Pattern.compile("^[0-9]+ - ");
      int freq = 0;

      while((line = br.readLine()) != null) {
        Matcher m = matchNum.matcher(line);

        if(m.matches()) {
          freq = Integer.parseInt(line.split(" ")[0]);

        } else {
          line = line.trim();

          if(line == "") continue;

          NPList.put(line, freq);       
        }
      }
    
    } catch(IOException e) {
      e.printStackTrace();
    
    } finally {
      if(br != null) br.close();

      if(fr != null) fr.close();
    }

    HashMap<String, int[]> ranking = new HashMap<String, int[]>();

    /* Gathering 4 values: total freq, freq as part of other strings,
     * number of these longer strings, length of string */
    for(Map.Entry<String, Integer> entry : NPList.entrySet()) {

      String s = entry.getKey();

      if(s.length() == 1) continue;

      int charLength = (s.replaceAll(" ", "")).length();
      
      int[] quad = new int[4];
      quad[0] = entry.getValue();
      quad[1] = 0;
      quad[2] = 0;
      quad[3] = charLength;

      /* Searching other strings */
      for(Map.Entry<String, Integer> e : NPList.entrySet()) {
        if(e.getKey() == entry.getKey()) continue;

        if(e.getKey().contains(entry.getKey())) {
          quad[1] = quad[1] + e.getValue();
          quad[2] = quad[2] + 1;
        }
      }

      ranking.put(entry.getKey(), quad);
    }

    out.println("total frequency in corpus, frequency as a substring, number of longer strings, length of string");
    
    for(Map.Entry<String, int[]> entry : ranking.entrySet()) {
      int[] quad = entry.getValue();
      out.println(entry.getKey() + ": " + quad[0] + ", " + quad[1] + ", " + quad[2] + ", " + quad[3]);
    }
		
    out.close();

    TreeMap<Integer, ArrayList<String>> termhood = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
    
    /* Calculate Termhood */
    for(Map.Entry<String, int[]> entry : ranking.entrySet()) {
      double c = 0;
      int freqInCorpus = entry.getValue()[0];
      int freqAsSubstring = entry.getValue()[1];
      int numStringsContainingThis = entry.getValue()[2];
      int lengthOfThis = entry.getValue()[3];
      int numWords = entry.getKey().split("\\s+").length;

      // Nested
      if(numStringsContainingThis > 0) {
        c = ((Math.log(lengthOfThis) / Math.log(2))) * ((freqInCorpus - (freqAsSubstring/numStringsContainingThis)));
        c = scale(c);
      } else { // Not Nested
        c = ((Math.log(lengthOfThis) / Math.log(2))) * freqInCorpus;
        c = scale(c);
      }

      /* Length check */
      if(numWords >= 4) {
        c = lengthPenalty(c, numWords);
      }

      int keyC = (int) c;

      if(termhood.containsKey(keyC)) {
        ArrayList<String> temp = termhood.get(keyC);
        temp.add(entry.getKey());
        termhood.put(keyC, temp);
      } else {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(entry.getKey());
        termhood.put(keyC, temp);
      }
    }

    /* Print out termhood */
    for(Map.Entry<Integer, ArrayList<String>> entry : termhood.entrySet()) {
      ArrayList<String> strings = entry.getValue();
      termfile.println(entry.getKey() + " - string count: " + entry.getValue().size());

      for(String s : strings) {
        termfile.println("\t" + s);
      }
    }

		long endTime   = System.currentTimeMillis();
	  long totalTime = endTime - startTime;
	  System.out.println(totalTime);
	}

  public static double scale(double x) {
    int yfactor = 200;
    int xfactor = 20;

    double sigmoid = 1 / (1 + Math.exp((x/xfactor) * (-1)));

    return (double) (yfactor * sigmoid);
  }

  public static double lengthPenalty(double score, int numWords) {
    int m = 3;
    int lengthThreshold = 4;

    double newScore = (1 - (m * (numWords-lengthThreshold) / 100)) * score;
    return newScore;
  }
}