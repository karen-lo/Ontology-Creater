package OntologyInsertion;

public class Response {
    private String uri;
    private String[] labels;
    private String fragment;
    private String curie;
    private String[] categories;
    private String[] synonyms;
    private String[] acronyms;
    private String[] abbreviations;
    private boolean deprecated;
    private String[] definitions;

    Response() {

    }

    public String getURI() {
        return this.uri;
    }

    public String[] getLabels() {
        return this.labels;
    }

    public String getFragment() {
        return this.fragment;
    }

    public String getCurie() {
        return this.curie;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public String[] getSynonyms() {
        return this.synonyms;
    }

    public String[] getAcronyms() {
        return this.acronyms;
    }

    public String[] getAbbreviations() {
        return this.abbreviations;
    }

    public boolean getDeprecated() {
        return this.deprecated;
    }

    public String[] getDefinitions() {
        return this.definitions;
    }
}
