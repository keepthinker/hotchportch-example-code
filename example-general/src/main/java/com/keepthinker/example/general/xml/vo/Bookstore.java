package com.keepthinker.example.general.xml.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "com.keepthinker.example.general.xml.jaxb")
public class Bookstore {

    @XmlElementWrapper(name = "bookList")
    @XmlElement(name = "book")
    private List <Book> bookList;
    private String name;
    private String location;

    public void setBookList(List < Book > bookList) {
        this.bookList = bookList;
    }

    public List < Book > getBooksList() {
        return bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}