package com.keepthinker.example.general.security.hutool;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.util.Utils;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.security.KeyPair;

/**
 * 参考文献：https://www.jianshu.com/p/5d50431570cf
 */
public class Sm2Main {

    public static void main(String[] args) throws Exception {

        sm2Test();
    }

    private static void genKeyPair() throws Exception {
        SM2 sm2 = new SM2();
        System.out.println(Utils.encode(EncodeType.HEX, ((BCECPrivateKey)sm2.getPrivateKey()).getD().toByteArray()));
        System.out.println(Utils.encode(EncodeType.HEX, ((BCECPublicKey)sm2.getPublicKey()).getQ().getEncoded(true)));

        System.out.println(Utils.encode(EncodeType.BASE64, sm2.getPrivateKey().getEncoded()));
        System.out.println(Utils.encode(EncodeType.BASE64, sm2.getPublicKey().getEncoded()));
    }

    private static void sm2Test() {

        String data = "certification is Certification(id=5fb5e11958371b3e7362d7fc, portEntry=470605, entryDate=2020-10-10, orderNo=22011191052041380(9622), province=广东省, weight=80.0, consignee=深圳市龙华区永嘉鑫冻品批发行, consigner=深圳市龙岗区新锦华冻品批发行, batchInfoList=[BatchInfo(id=null, batchNo=20200817, countryOfOrigin=303, productName=无, customName=猪小排, specification=10公斤/件, productType=LIS, weight=40.0)], updateTime=Thu Nov 19 11:06:01 CST 2020)";

        //使用自定义密钥对加密或解密
//        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        KeyPair pair = SecureUtil.generateKeyPair("SM2", 256);
//        byte[] privateKey = pair.getPrivate().getEncoded();
//        byte[] publicKey = pair.getPublic().getEncoded();


        BCECPrivateKey exPrivateKey = (BCECPrivateKey) pair.getPrivate();
        BCECPublicKey ecPublicKey = (BCECPublicKey) pair.getPublic();

        byte[] privateKey = exPrivateKey.getD().toByteArray();
        byte[] publicKey = ecPublicKey.getQ().getEncoded(true);

        String privateKeyStr = Hex.encodeHexString(privateKey);
        String publicKeyStr = Hex.encodeHexString(publicKey);

//        String privateKeyStr = "783736d44a2d16e0e6ea42d8b66f6845cea5500ae9e45bbfd4452f15d6d135b5";
//        String publicKeyStr = "033156a0ffc95f2f5b03701f9d7f46b65996526ab3765943e7abf7ac216e1e67e9";
        System.out.println("private key hex: " + privateKeyStr);
        System.out.println("public key hex: " + publicKeyStr);


        SM2 sm2 = SmUtil.sm2(privateKeyStr, publicKeyStr);
        // 公钥加密，私钥解密
        String encryptBcdStr = sm2.encryptBcd(data, KeyType.PublicKey);
        System.out.println("encryptBcd:" + encryptBcdStr);

        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptBcdStr, KeyType.PrivateKey));
        System.out.println(decryptStr);


        // 公钥加密，私钥解密
        String encryptHexStr = sm2.encryptHex(data, KeyType.PublicKey);
        System.out.println("encryptHexStr:" + encryptHexStr);
    }
}
