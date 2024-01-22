package com.keepthinker.example.general.xml.hutool;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONUtil;
import org.w3c.dom.Document;

public class XmlMain {

    public static void main(String[] args) {


        Document document = XmlUtil.parseXml("<book>\n" +
                "            <author>Joshua Bloch</author>\n" +
                "            <title>Effective Java</title>\n" +
                "            <publisher>Amazon</publisher>\n" +
                "            <isbn>978-0134685991</isbn>\n" +
                "            <publish_date>2023-11-07</publish_date>\n" +
                "        </book>");

        Book book = XmlUtil.xmlToBean(XmlUtil.getRootElement(document), Book.class);

        System.out.println(JSONUtil.toJsonPrettyStr(book));
    }
}
