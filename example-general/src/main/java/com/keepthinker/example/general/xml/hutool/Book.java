package com.keepthinker.example.general.xml.hutool;


import jakarta.xml.bind.annotation.XmlAttribute;

public class Book {

    private String title;
    private String author;

    @XmlAttribute(name = "publish_date")
    private String publishDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
