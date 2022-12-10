package com.keepthinker.example.general.security.rsa;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.security.StringKeyPair;
import com.keepthinker.example.general.util.Utils;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {
    private static final ThreadLocal<Cipher> cipherCache = new ThreadLocal<>();

    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final String SIGN_ALGORITHM = "SHA256withRSA";

    public static StringKeyPair generateKeyPair(EncodeType encodeType) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] privateKey = keyPair.getPrivate().getEncoded();
        byte[] publicKey = keyPair.getPublic().getEncoded();

        StringKeyPair stringKeyPair = new StringKeyPair();
        switch (encodeType) {
            case HEX:
                stringKeyPair.setPrivateKey(Hex.encodeHexString(privateKey));
                stringKeyPair.setPublicKey(Hex.encodeHexString((publicKey)));
                break;
            case BASE64:
                stringKeyPair.setPrivateKey(Base64.getEncoder().encodeToString(privateKey));
                stringKeyPair.setPublicKey(Base64.getEncoder().encodeToString(publicKey));
                break;
            case BASE64_MIME:
                stringKeyPair.setPrivateKey(Base64.getMimeEncoder().encodeToString(privateKey));
                stringKeyPair.setPublicKey(Base64.getMimeEncoder().encodeToString(publicKey));
                break;
            case BASE64_URL_SAFE:
                stringKeyPair.setPrivateKey(Base64.getUrlEncoder().encodeToString(privateKey));
                stringKeyPair.setPublicKey(Base64.getUrlEncoder().encodeToString(publicKey));
                break;
        }
        return stringKeyPair;
    }

    public static byte[] encrypt(EncodeType keyEncodeType, String publicKey, byte[] data) throws Exception {
        PublicKey pubKey = getPublicKey(keyEncodeType, publicKey);

        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(cipher);
        }
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    private static PublicKey getPublicKey(EncodeType keyEncodeType, String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Utils.decode(keyEncodeType, publicKey));
        return keyFactory.generatePublic(keySpec);
    }

    public static byte[] decrypt(EncodeType keyEncodeType, String privateKey, byte[] data) throws Exception {
//        RSAPrivateKey pubKey =  (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Utils.decode(keyEncodeType, privateKey)));
        PrivateKey pubKey = getPrivateKey(keyEncodeType, privateKey);
        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(cipher);
        }
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    private static PrivateKey getPrivateKey(EncodeType keyEncodeType, String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] decodedKey = Utils.decode(keyEncodeType, privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    public static byte[] sign(EncodeType keyEncodeType, String privateKey, byte[] data) throws Exception {
        PrivateKey key = getPrivateKey(keyEncodeType, privateKey);
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(key);
        signature.update(data);
        return signature.sign();
    }

    public static boolean verify(EncodeType keyEncodeType, String publicKey, byte[] srcData, byte[] sign) throws Exception {
        PublicKey key = getPublicKey(keyEncodeType, publicKey);
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(key);
        signature.update(srcData);
        return signature.verify(sign);
    }


}
