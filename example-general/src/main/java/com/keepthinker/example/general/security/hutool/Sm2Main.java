package com.keepthinker.example.general.security.hutool;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.apache.commons.codec.binary.Hex;

import java.security.KeyPair;

/**
 * 参考文献：https://www.jianshu.com/p/5d50431570cf
 */
public class Sm2Main {

    public static void main(String[] args) {
        String data = "certification is Certification(id=5fb5e11958371b3e7362d7fc, portEntry=470605, entryDate=2020-10-10, orderNo=22011191052041380(9622), province=广东省, weight=80.0, consignee=深圳市龙华区永嘉鑫冻品批发行, consigner=深圳市龙岗区新锦华冻品批发行, batchInfoList=[BatchInfo(id=null, batchNo=20200817, countryOfOrigin=303, productName=无, customName=猪小排, specification=10公斤/件, productType=LIS, weight=40.0)], updateTime=Thu Nov 19 11:06:01 CST 2020)";

        //使用自定义密钥对加密或解密
//        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        KeyPair pair = SecureUtil.generateKeyPair("SM2", 256);
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        String privateKeyStr = Hex.encodeHexString(privateKey);
        String publicKeyStr = Hex.encodeHexString(publicKey);

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
