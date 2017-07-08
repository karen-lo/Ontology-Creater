import java.io.*;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Combine Hashmaps 
public class CombineHashmaps {

  /** Usage: java CombineHashmaps Hashmap1 Hashmap2 outfile **/
	public static void main(String args[]) throws IOException {
		long startTime = System.currentTimeMillis();

		PrintWriter out;
    if (args.length == 3) {
      out = new PrintWriter(args[2]);
    } else {
      out = new PrintWriter(System.out);
      System.out.println("Wrong usage.");
      System.exit(0);
    }

    BufferedReader br = null;
    FileReader fr = null;
    HashMap<String, Integer> NPList = new HashMap<String, Integer>();

    /* Process first hashmap */
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

    /* Process second hashmap */
    try {
      fr = new FileReader(args[1]);
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

          if(NPList.containsKey(line)) {
            NPList.put(line, NPList.get(line)+freq);
          
          } else {
            NPList.put(line, freq);
          }
        }
      }
    
    } catch(IOException e) {
      e.printStackTrace();
    
    } finally {
      if(br != null) br.close();

      if(fr != null) fr.close();
    }

		TreeMap<Integer, ArrayList<String>> freqList = new TreeMap<Integer, ArrayList<String>>();

    /* Map strings to frequencies */
    for(Map.Entry<String, Integer> entry : NPList.entrySet()) {
      
      if(freqList.containsKey(entry.getValue())) {
        ArrayList<String> temp = freqList.get(entry.getValue());
        temp.add(entry.getKey());
        freqList.put(entry.getValue(), temp);
      
      } else {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(entry.getKey());
        freqList.put(entry.getValue(), temp);
      }
    }

    /* Printing out */
    for(Map.Entry<Integer, ArrayList<String>> entry : freqList.entrySet()) {
      out.println(entry.getKey() + " - ");
      
      ArrayList<String> temp = entry.getValue();
      for(int i=0; i<temp.size(); i++) {
        out.println("\t" + temp.get(i));
      }
    }

    out.close();

		long endTime   = System.currentTimeMillis();
	  long totalTime = endTime - startTime;
	  System.out.println(totalTime);
	}
}