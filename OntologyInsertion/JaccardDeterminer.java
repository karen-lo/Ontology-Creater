package OntologyInsertion;

import com.google.gson.Gson;
import info.debatty.java.stringsimilarity.Jaccard;
import java.nio.charset.StandardCharsets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class JaccardDeterminer {

    static final String PATH = "C:\\Users\\Karen\\IdeaProjects\\Ontology-Creator\\OntologyInsertion\\";
    static final String SERVER_URL = "http://ec-scigraph.sdsc.edu:9000/";
    static final int NUM_SHINGLES = 2;
    static final double THRESHOLD_SIMILARITIES = 0.6;
    static Jaccard jaccard = new Jaccard(NUM_SHINGLES);

    static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {

        if(args.length != 1) {
            System.out.println("Wrong Usage.");
            System.exit(0);
        }

        PrintWriter in_ontology = new PrintWriter(PATH + "ICRS_in_ontology.tsv", "UTF-8");
        PrintWriter not_in_ontology = new PrintWriter(PATH + "ICRS_unique.tsv", "UTF-8");
        in_ontology.println("ICRS term\tsimilarity\tcinergi term");
        not_in_ontology.println("ICRS term\tsimilarity\tclosest cinergi term");

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(args[0]));
            String line;

            while((line = br.readLine()) != null) {
                ArrayList<Response> responses = getTerm(line, false);

                if(responses == null) {
                    // no match
                    responses = getTerm(line, true);

                    if(responses == null) {
                        // no close match
                        not_in_ontology.println(line);
                        continue;
                    }

                    double max_similarity = 0.0;
                    String closest_label = "";

                    for(Response r : responses) {
                        for(String label : r.getLabels()) {
                            double temp = jaccard.similarity(line.toLowerCase(), label.toLowerCase());

                            if(temp > max_similarity) {
                                // find closest match
                                closest_label = label;
                                max_similarity = temp;
                            }
                        }
                    }

                    if(max_similarity < THRESHOLD_SIMILARITIES) {
                        // closest match no close enough
                        not_in_ontology.println(line + "\t" + max_similarity + "\t" + closest_label);
                        continue;
                    }

                    // closest match
                    in_ontology.println(line + "\t" + max_similarity + "\t" + closest_label);

                } else {
                    // 100% match
                    in_ontology.println(line + "\t1.0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        in_ontology.close();
        not_in_ontology.close();
    }

    public static ArrayList<Response> getTerm(String term, boolean search) throws Exception {
        if(term == null) {
            return null;
        }

        String prefix = SERVER_URL + "scigraph/vocabulary/" +  ((search == true) ? "search/" : "term/");
        String suffix = "?limit=10&searchSynonyms=true&searchAbbreviations=false&searchAcronyms=false";
        String urlTerm = URLEncoder.encode(term, StandardCharsets.UTF_8.name()).replace("+", "%20");
        String url = prefix + urlTerm + suffix;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        if(con.getResponseCode() == 404) {
//            System.out.println("'GET' request failed for URL: " + url);
            return null;
        }
//        System.out.println("\nSent 'GET' request to URL : " + url);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Gson gson = new Gson();
        Response[] responses = gson.fromJson(response.toString(), Response[].class);
        return new ArrayList<Response>(Arrays.asList(responses));
    }
}
