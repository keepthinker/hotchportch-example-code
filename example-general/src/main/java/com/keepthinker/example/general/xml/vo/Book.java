package com.keepthinker.example.general.xml.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
@XmlType(propOrder = {
        "author",
        "name",
        "publisher",
        "isbn",
        "publishDate"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private String name;
    private String author;
    private String publisher;
    private String isbn;


    @XmlElement(name = "publish_date")
    private String publishDate;

    @XmlElement(name = "title")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}