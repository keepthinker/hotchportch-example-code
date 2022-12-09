package com.keepthinker.example.general.security.aes;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.util.Utils;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AesUtils {
    private static final ThreadLocal<Cipher> cipherCache = new ThreadLocal<>();

    public static final String KEY_ALGORITHM="AES";

    /**
     * 加密/解密算法/工作模式/填充方式
     * JAVA6 支持PKCS5PADDING填充方式
     * Bouncy castle支持PKCS7Padding填充方式
     * */
    public static final String CIPHER_ALGORITHM="AES/ECB/PKCS5Padding";

    public static String generateKey(EncodeType encodeType) throws NoSuchAlgorithmException {
        KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
        KeyGen.init(128);

        byte[] secretKey = KeyGen.generateKey().getEncoded();
        switch (encodeType) {
            case HEX:
                return Hex.encodeHexString(secretKey);
            case BASE64:
                return Base64.getEncoder().encodeToString(secretKey);
            case BASE64_MIME:
                return Base64.getMimeEncoder().encodeToString(secretKey);
            case BASE64_URL_SAFE:
                return Base64.getUrlEncoder().encodeToString(secretKey);
        }
        return null;
    }

    public static byte[] encrypt(EncodeType keyEncodeType, String secretKey, byte[] data) throws Exception {
        Cipher aesCipher = cipherCache.get();
        if (aesCipher == null) {
            aesCipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(aesCipher);
        }
        aesCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Utils.decode(keyEncodeType, secretKey), "AES"));
        return aesCipher.doFinal(data);

    }


    public static byte[] decrypt(EncodeType keyEncodeType, String secretKey, byte[] data) throws Exception {

        Cipher aesCipher = cipherCache.get();
        if (aesCipher == null) {
            aesCipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(aesCipher);
        }

        aesCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Utils.decode(keyEncodeType, secretKey), "AES"));
        return aesCipher.doFinal(data);
    }
}
