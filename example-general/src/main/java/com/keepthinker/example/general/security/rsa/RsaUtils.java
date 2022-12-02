package com.keepthinker.example.general.security.rsa;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.security.StringKeyPair;
import com.keepthinker.example.general.util.Utils;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {
    private static final ThreadLocal<Cipher> cipherCache = new ThreadLocal<>();

    public static final String KEY_ALGORITHM="RSA";

    public static final String CIPHER_ALGORITHM="RSA/ECB/PKCS1Padding";

    public static StringKeyPair generateKeyPair(EncodeType encodeType) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] privateKey = keyPair.getPrivate().getEncoded();
        byte[] publicKey = keyPair.getPublic().getEncoded();

        StringKeyPair base64KeyPair  = new StringKeyPair();
        switch (encodeType) {
            case HEX:
                base64KeyPair.setPrivateKey(Hex.encodeHexString(privateKey));
                base64KeyPair.setPublicKey(Hex.encodeHexString((publicKey)));
                break;
            case BASE64:
                base64KeyPair.setPrivateKey(Base64.getEncoder().encodeToString(privateKey));
                base64KeyPair.setPublicKey(Base64.getEncoder().encodeToString(publicKey));
                break;
            case BASE64_MIME:
                base64KeyPair.setPrivateKey(Base64.getMimeEncoder().encodeToString(privateKey));
                base64KeyPair.setPublicKey(Base64.getMimeEncoder().encodeToString(publicKey));
                break;
            case BASE64_URL_SAFE:
                base64KeyPair.setPrivateKey(Base64.getUrlEncoder().encodeToString(privateKey));
                base64KeyPair.setPublicKey(Base64.getUrlEncoder().encodeToString(publicKey));
                break;
        }
        return base64KeyPair;
    }

    public static byte[] encrypt(EncodeType keyEncodeType, String publicKey, byte[] data) throws Exception {
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Utils.decode(keyEncodeType, publicKey)));

        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(cipher);
        }
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(EncodeType keyEncodeType, String privateKey, byte[] data) throws Exception {
        RSAPrivateKey pubKey =  (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Utils.decode(keyEncodeType, privateKey)));

        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(cipher);
        }
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

}
