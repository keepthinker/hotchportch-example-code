package com.keepthinker.example.general.security.sm2;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.security.StringKeyPair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 参考：https://blog.csdn.net/qq_27631797/article/details/120045171
 */
public class Sm2Main {

    public static void main(String[] args) throws Exception {
        int threads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        EncodeType encodeType = EncodeType.HEX;
        StringKeyPair keyPair = Sm2Utils.generateKeyPair(encodeType);
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 2; j++) {
                    try {
                        byte[] encryptedData = Sm2Utils.encrypt(encodeType, keyPair.getPublicKey(), "hell world!".getBytes());
                        byte[] decryptedData = Sm2Utils.decrypt(encodeType, keyPair.getPrivateKey(), encryptedData);
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
