package com.keepthinker.example.general.security;

import com.keepthinker.example.general.security.rsa.RsaUtils;

public class EncryptMain {
    public static void main(String[] args) throws Exception {
        EncodeType encodeType = EncodeType.HEX;
        StringKeyPair keyPair = RsaUtils.generateKeyPair(encodeType);
        byte[] encryptedData = RsaUtils.encrypt(encodeType, keyPair.getPublicKey(), "hell world!".getBytes());
        byte[] decryptedData = RsaUtils.decrypt(encodeType, keyPair.getPrivateKey(), encryptedData);
        System.out.println(new String(decryptedData));
    }
}
