package OntologyInsertion;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class InOntologyExample {
    public static void main(String[] args) throws Exception {
        HashMap<String, Double> terms = inOntology("cow");

        if(terms == null) {
            System.out.println("No related terms found.");
        } else if(terms.size() == 0) {
            System.out.println("Match found in ontology.");
        } else {
            System.out.println("===============================================");
            for(Map.Entry<String, Double> entry : terms.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    private static HashMap<String, Double> inOntology(String term) throws Exception {
        ArrayList<String> matches = sendGetTerm(term, 20, false, false, false);
        HashMap<String, Double> relatedDict = new HashMap<String, Double>();

        if(matches != null) {
            // Found Match
            for(int i=0; i<matches.size(); i++) {
                System.out.println(matches.get(i));
            }
            return relatedDict;

        } else {
            System.out.println("No exact match. Looking for related terms...");
            ArrayList<String> relatedTerms = sendGetSearch(term, 20, true, true, false);

            if(relatedTerms != null) {
                ArrayList<String> previousTerms = new ArrayList<String>();

                for (String relatedTerm : relatedTerms) {
                    String changed = relatedTerm.replaceAll("[^a-zA-Z]", "").toLowerCase();

                    int intersectionCount = 0;
                    int unionCount = 0;

                    for (int j = 0; j < term.length(); j++) {

                        if (changed.contains(term.substring(0, term.length() - j))) {
                            intersectionCount = j;
                        } else {
                            break;
                        }
                    }

                    Set<Character> union = new HashSet<Character>();
                    for (int x=0; x<term.length(); x++) union.add(term.charAt(x));
                    for (int x=0; x<changed.length(); x++) union.add(changed.charAt(x));
                    unionCount = union.size();

                    double jaccard = (double) intersectionCount/unionCount;

                    if(!previousTerms.contains(changed)) {
                        previousTerms.add(changed);
                        relatedDict.put(relatedTerm, jaccard);
                    }
                }

                return relatedDict;
            }

            // No Related Terms Found
            return null;
        }


    }

    private static ArrayList<String> sendGetTerm(String term, int limit, boolean synon, boolean abbrev,
                                                 boolean acron) throws Exception {

        String baseURL = "http://ec-scigraph.sdsc.edu:9000/scigraph/vocabulary/term/" + term + "?";
        String limitParam = "limit=" + limit;
        String synonymParam = "searchSynonyms=" + synon;
        String abrreviationParam = "searchAbbreviations=" + abbrev;
        String acronymParam = "searchAcronyms=" + acron;

        String url = baseURL + limitParam + "&" + synonymParam + "&" +
                abrreviationParam + "&" + acronymParam;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        if(responseCode == 404)
            return null;

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String responseString = response.toString();
        System.out.println(responseString);

        Gson gson = new Gson();
        Response[] responses = gson.fromJson(responseString, Response[].class);
        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0; i<responses.length; i++) {
            String[] l = responses[i].getLabels();
            for(int j=0; j<l.length; j++) {
                labels.add(l[j]);
            }
        }
        return labels;
    }

    private static ArrayList<String> sendGetSearch(String term, int limit, boolean synon, boolean abbrev,
                                                   boolean acron) throws Exception {

        String baseURL = "http://ec-scigraph.sdsc.edu:9000/scigraph/vocabulary/search/" + term + "?";
        String limitParam = "limit=" + limit;
        String synonymParam = "searchSynonyms=" + synon;
        String abrreviationParam = "searchAbbreviations=" + abbrev;
        String acronymParam = "searchAcronyms=" + acron;

        String url = baseURL + limitParam + "&" + synonymParam + "&" +
                abrreviationParam + "&" + acronymParam;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        if(responseCode == 404)
            return null;

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String responseString = response.toString();
        System.out.println(responseString);

        Gson gson = new Gson();
        Response[] responses = gson.fromJson(responseString, Response[].class);
        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0; i<responses.length; i++) {
            String[] l = responses[i].getLabels();
            for(int j=0; j<l.length; j++) {
                labels.add(l[j]);
            }
        }
        return labels;
    }
}