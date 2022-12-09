package com.keepthinker.example.general.security.ecc;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.security.StringKeyPair;
import com.keepthinker.example.general.util.Utils;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EccUtils {

    public static final String KEY_ALGORITHM = "EC";

    public static final String CIPHER_ALGORITHM = "ECIES";

    private static final ThreadLocal<Cipher> cipherCache = new ThreadLocal<>();
    /**
     * @see org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi ecParameters (line #173)
     * 192, 224, 239, 256, 384, 521
     */
    private final static int KEY_SIZE = 256;

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public static StringKeyPair generateKeyPair(EncodeType encodeType) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);//BouncyCastle
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
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

    //公钥加密
    public static byte[] encrypt(EncodeType keyEncodeType, String publicKey, byte[] data) throws Exception {
        ECPublicKey pubKey = (ECPublicKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Utils.decode(keyEncodeType, publicKey)));

        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(cipher);
        }
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(EncodeType keyEncodeType, String privateKey, byte[] data) throws Exception {
        ECPrivateKey pubKey = (ECPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Utils.decode(keyEncodeType, privateKey)));

        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherCache.set(cipher);
        }
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }


}
