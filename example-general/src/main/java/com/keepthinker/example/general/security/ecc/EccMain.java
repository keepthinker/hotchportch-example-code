package com.keepthinker.example.general.security.ecc;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.security.StringKeyPair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// 参考： https://blog.csdn.net/qq_36319965/article/details/122564782
// https://blog.csdn.net/zhouqilong970/article/details/78126319
public class EccMain {

    public static void main(String[] args) throws Exception {
        int threads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        EncodeType encodeType = EncodeType.HEX;
        StringKeyPair keyPair = EccUtils.generateKeyPair(encodeType);
        System.out.printf("private key(%d): %s\n", keyPair.getPrivateKey().length(), keyPair.getPrivateKey());
        System.out.printf("public key(%d): %s\n", keyPair.getPublicKey().length(), keyPair.getPublicKey());
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 2; j++) {
                    try {
                        byte[] encryptedData = EccUtils.encrypt(encodeType, keyPair.getPublicKey(), "hell world!".getBytes());
                        byte[] decryptedData = EccUtils.decrypt(encodeType, keyPair.getPrivateKey(), encryptedData);
                        System.out.println(new String(decryptedData));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
    }
}
