package com.keepthinker.example.general.xml.jackson;

import com.keepthinker.example.general.util.Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class JacksonXmlMain {

    private static final String BOOKSTORE_XML = "/xml/bookstore-jaxb.xml";


    public static void main(String[] args) throws IOException {
        String xmlStr = FileUtils.readFileToString(new File(Utils.getContextClasspath() + BOOKSTORE_XML), "utf-8");
        TreeMap map = JacksonXmlUtils.xmlToObject(xmlStr, TreeMap.class);

        System.out.println(map);
    }
}
