package com.keepthinker.example.general.security.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptMain {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("DOIJ239jDjisD");

        String encStr = encryptor.encrypt("hello world!");
        System.out.println(encryptor.decrypt(encStr));

    }
}
