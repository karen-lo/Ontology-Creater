import java.io.*;
import java.util.*;

public class NGrams {
	public static int main(String args[]) {

	}

	/* Comparing the new term term1 with the existing ontology term term2 ??*/
	double ngram(String term1, String term2) {
		unionCount = 0;
		jaccard = 0;

		// Empty strings
		if(term1.length() < 1 || term2.length() < 1) 
			return 0;

		// Find unionCount
		Set<Character> union = new HashSet<Character>();
		for(char c : term1) union.add(c);
		for(char c : term2) union.add(c);
		unionCount = union.size();

		if(term1.length() < 2 || term2.length() < 2) 
			return (1/unionCount);

		min = 2;
		max = 0;

		if(term1.length() > term2.length()) {
			max = term2.length();
		
		} else {
			max = term1.length();
		}

		// N Grams, starting at 2
		for(int n = min; n < max; n++) {
			intersectionCount = 0;
			tempJaccard = 0;

			for(int i = 0; i < (term1.length()-n); i++) {
				String substring = term1.substring(i, i+n);
				
				for(int j = 0; j < (term2.length()-n); j++) {
					
					if(substring.equals(term2.substring(j, j+n))) {
						intersectionCount += 1; //???
					}
				}				
			}

			tempJaccard = intersectionCount/unionCount;

			if(tempJaccard > jaccard) 
				jaccard = tempJaccard;
		}

	}
}

