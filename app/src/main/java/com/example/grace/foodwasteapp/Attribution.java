
package com.example.grace.foodwasteapp;


public class Attribution {

    private String html;
    private String url;
    private String text;
    private String logo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Attribution() {
    }

    /**
     * 
     * @param logo
     * @param text
     * @param html
     * @param url
     */
    public Attribution(String html, String url, String text, String logo) {
        super();
        this.html = html;
        this.url = url;
        this.text = text;
        this.logo = logo;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
