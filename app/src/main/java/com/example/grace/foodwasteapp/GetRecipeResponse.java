
package com.example.grace.foodwasteapp;

import java.util.ArrayList;
import java.util.List;

public class GetRecipeResponse {

    private Attribution attribution;
    private int totalMatchCount;
    private FacetCounts facetCounts;
    private List<Match> matches = null;
    private Criteria criteria;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetRecipeResponse() {
    }

    /**
     * 
     * @param matches
     * @param criteria
     * @param facetCounts
     * @param totalMatchCount
     * @param attribution
     */
    public GetRecipeResponse(Attribution attribution, int totalMatchCount, FacetCounts facetCounts, List<Match> matches, Criteria criteria) {
        super();
        this.attribution = attribution;
        this.totalMatchCount = totalMatchCount;
        this.facetCounts = facetCounts;
        this.matches = matches;
        this.criteria = criteria;
    }

    public Attribution getAttribution() {
        return attribution;
    }

    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    public int getTotalMatchCount() {
        return totalMatchCount;
    }

    public void setTotalMatchCount(int totalMatchCount) {
        this.totalMatchCount = totalMatchCount;
    }

    public FacetCounts getFacetCounts() {
        return facetCounts;
    }

    public void setFacetCounts(FacetCounts facetCounts) {
        this.facetCounts = facetCounts;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<String> getMatchNames(){
        List<String> matchNames = new ArrayList<String>();
        for(int k = 0; k < matches.size(); k++){
            matchNames.add(matches.get(k).getRecipeName());
        }
        return matchNames;
    }

}
