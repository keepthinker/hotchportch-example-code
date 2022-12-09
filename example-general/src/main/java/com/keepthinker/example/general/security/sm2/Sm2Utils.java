package com.keepthinker.example.general.security.sm2;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.security.StringKeyPair;
import com.keepthinker.example.general.util.Utils;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * SM2加密
 *
 * @author hcf
 * @date 2021/9/1 14:31
 */
public class Sm2Utils {
    public static final String KEY_ALGORITHM = "EC";

    public static final String CIPHER_ALGORITHM = "SM2";

    private static final ThreadLocal<Cipher> cipherCache = new ThreadLocal<>();

    /**
     * SM2 生成公私钥
     *
     * @return 公私钥
     */
    public static StringKeyPair generateKeyPair(EncodeType encodeType) throws Exception {
        BouncyCastleProvider provider = new BouncyCastleProvider();

        // 获取椭圆曲线相关生成参数规格
        ECGenParameterSpec genParameterSpec = new ECGenParameterSpec("sm2p256v1");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", provider);

        keyPairGenerator.initialize(genParameterSpec, new SecureRandom());

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        BCECPrivateKey exPrivateKey = (BCECPrivateKey) keyPair.getPrivate();
        BCECPublicKey ecPublicKey = (BCECPublicKey) keyPair.getPublic();

        byte[] privateKey = exPrivateKey.getD().toByteArray();
        byte[] publicKey = ecPublicKey.getQ().getEncoded(true);
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

    /**
     * sm2 加密
     *
     * @param publicKey 公钥
     * @param data      内容
     * @return 密文
     */
    public static byte[] encrypt(EncodeType keyEncodeType, String publicKey, byte[] data) throws Exception {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        // 获取SM2相关参数
        X9ECParameters parameters = GMNamedCurves.getByName("sm2p256v1");
        // 椭圆曲线参数规格
        ECParameterSpec ecParameterSpec = new ECParameterSpec(parameters.getCurve(), parameters.getG(), parameters.getN(), parameters.getH());
        // 将公钥HEX字符串转换为椭圆曲线对应的点
        ECPoint ecPoint = parameters.getCurve().decodePoint(Utils.decode(keyEncodeType, publicKey));
        // 获取椭圆曲线KEY生成器
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, provider);
        // 将椭圆曲线点转为公钥KEY对象
        BCECPublicKey bcecPublicKey = (BCECPublicKey) keyFactory.generatePublic(new ECPublicKeySpec(ecPoint, ecParameterSpec));

        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM, provider);
            cipherCache.set(cipher);
        }
        // 初始化为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, bcecPublicKey);
        return cipher.doFinal(data);
    }


    /**
     * sm2 加密
     *
     * @param privateKey 私钥
     * @param data       密文
     * @return 明文
     */
    public static byte[] decrypt(EncodeType keyEncodeType, String privateKey, byte[] data) throws Exception {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        // 获取SM2相关参数
        X9ECParameters parameters = GMNamedCurves.getByName("sm2p256v1");
        // 椭圆曲线参数规格
        ECParameterSpec ecParameterSpec = new ECParameterSpec(parameters.getCurve(), parameters.getG(), parameters.getN(), parameters.getH());
//         将私钥HEX字符串转换为X值
            BigInteger bigInteger = new BigInteger(Utils.decode(keyEncodeType, privateKey));
        // 获取椭圆曲线KEY生成器
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, provider);
        // 将X值转为私钥KEY对象
        BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) keyFactory.generatePrivate(new ECPrivateKeySpec(bigInteger, ecParameterSpec));

        Cipher cipher = cipherCache.get();
        if (cipher == null) {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM, provider);
            cipherCache.set(cipher);
        }
        // 初始化为加密模式
        cipher.init(Cipher.DECRYPT_MODE, bcecPrivateKey);
        // 解密
        return cipher.doFinal(data);

    }

}