package com.keepthinker.example.general.xml.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonXmlUtils {
    private static ObjectMapper objectMapper = XmlMapper.builder().build();

    public static String objectToXml(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T xmlToObject(String xml, Class<T> clazz) {
        try {
            return objectMapper.readValue(xml, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
