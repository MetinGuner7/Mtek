package com.mghg.mtek.models;

public class Xmlitems {

    private String title, link, pubDate, enclosure;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public Xmlitems(String title, String link, String pubDate, String enclosure) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.enclosure = enclosure;
    }
}
