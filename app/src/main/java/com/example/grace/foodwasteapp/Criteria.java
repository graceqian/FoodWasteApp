
package com.example.grace.foodwasteapp;

import java.util.List;

public class Criteria {

    private int maxResults;
    private List<String> excludedIngredients = null;
    private List<Object> excludedAttributes = null;
    private List<String> allowedIngredients = null;
    private AttributeRanges attributeRanges;
    private NutritionRestrictions nutritionRestrictions;
    private List<String> allowedDiets = null;
    private int resultsToSkip;
    private boolean requirePictures;
    private List<Object> facetFields = null;
    private List<String> terms = null;
    private List<String> allowedAttributes = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Criteria() {
    }

    /**
     * 
     * @param resultsToSkip
     * @param allowedDiets
     * @param excludedAttributes
     * @param terms
     * @param allowedIngredients
     * @param attributeRanges
     * @param requirePictures
     * @param excludedIngredients
     * @param allowedAttributes
     * @param facetFields
     * @param maxResults
     * @param nutritionRestrictions
     */
    public Criteria(int maxResults, List<String> excludedIngredients, List<Object> excludedAttributes, List<String> allowedIngredients, AttributeRanges attributeRanges, NutritionRestrictions nutritionRestrictions, List<String> allowedDiets, int resultsToSkip, boolean requirePictures, List<Object> facetFields, List<String> terms, List<String> allowedAttributes) {
        super();
        this.maxResults = maxResults;
        this.excludedIngredients = excludedIngredients;
        this.excludedAttributes = excludedAttributes;
        this.allowedIngredients = allowedIngredients;
        this.attributeRanges = attributeRanges;
        this.nutritionRestrictions = nutritionRestrictions;
        this.allowedDiets = allowedDiets;
        this.resultsToSkip = resultsToSkip;
        this.requirePictures = requirePictures;
        this.facetFields = facetFields;
        this.terms = terms;
        this.allowedAttributes = allowedAttributes;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public List<String> getExcludedIngredients() {
        return excludedIngredients;
    }

    public void setExcludedIngredients(List<String> excludedIngredients) {
        this.excludedIngredients = excludedIngredients;
    }

    public List<Object> getExcludedAttributes() {
        return excludedAttributes;
    }

    public void setExcludedAttributes(List<Object> excludedAttributes) {
        this.excludedAttributes = excludedAttributes;
    }

    public List<String> getAllowedIngredients() {
        return allowedIngredients;
    }

    public void setAllowedIngredients(List<String> allowedIngredients) {
        this.allowedIngredients = allowedIngredients;
    }

    public AttributeRanges getAttributeRanges() {
        return attributeRanges;
    }

    public void setAttributeRanges(AttributeRanges attributeRanges) {
        this.attributeRanges = attributeRanges;
    }

    public NutritionRestrictions getNutritionRestrictions() {
        return nutritionRestrictions;
    }

    public void setNutritionRestrictions(NutritionRestrictions nutritionRestrictions) {
        this.nutritionRestrictions = nutritionRestrictions;
    }

    public List<String> getAllowedDiets() {
        return allowedDiets;
    }

    public void setAllowedDiets(List<String> allowedDiets) {
        this.allowedDiets = allowedDiets;
    }

    public int getResultsToSkip() {
        return resultsToSkip;
    }

    public void setResultsToSkip(int resultsToSkip) {
        this.resultsToSkip = resultsToSkip;
    }

    public boolean isRequirePictures() {
        return requirePictures;
    }

    public void setRequirePictures(boolean requirePictures) {
        this.requirePictures = requirePictures;
    }

    public List<Object> getFacetFields() {
        return facetFields;
    }

    public void setFacetFields(List<Object> facetFields) {
        this.facetFields = facetFields;
    }

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public List<String> getAllowedAttributes() {
        return allowedAttributes;
    }

    public void setAllowedAttributes(List<String> allowedAttributes) {
        this.allowedAttributes = allowedAttributes;
    }

}
