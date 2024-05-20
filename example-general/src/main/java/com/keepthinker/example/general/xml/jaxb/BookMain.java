package com.keepthinker.example.general.xml.jaxb;

import com.keepthinker.example.general.util.Utils;
import com.keepthinker.example.general.xml.vo.Book;
import com.keepthinker.example.general.xml.vo.Bookstore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Unmarshaller Class - Convert XML to Object in Java
 * @author Ramesh Fadatare
 *
 */
public class BookMain {
    private static final String BOOKSTORE_XML = "/xml/bookstore-jaxb.xml";

    public static void main(String[] args) throws JAXBException, IOException {

        List <Book> bookList = new ArrayList < Book > ();

        // create books
        Book book1 = new Book();
        book1.setIsbn("978-0134685991");
        book1.setName("Effective Java");
        book1.setAuthor("Joshua Bloch");
        book1.setPublisher("Amazon");
        bookList.add(book1);

        Book book2 = new Book();
        book2.setIsbn("978-0596009205");
        book2.setName("Head First Java, 2nd Edition");
        book2.setAuthor("Kathy Sierra");
        book2.setPublisher("amazon");
        bookList.add(book2);

        // create bookstore, assigning book
        Bookstore bookstore = new Bookstore();
        bookstore.setName("Amazon Bookstore");
        bookstore.setLocation("Newyorkt");
        bookstore.setBookList(bookList);

        convertXMLToObject();
    }

    private static Bookstore convertXMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(Bookstore.class);
            Unmarshaller un = context.createUnmarshaller();
            Bookstore bookstore = (Bookstore) un.unmarshal(new File(Utils.getContextClasspath() + BOOKSTORE_XML));
            List < Book > list = bookstore.getBooksList();
            for (Book book: list) {
                System.out.println("Book: " + book.getName() + " from " + book.getAuthor());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}