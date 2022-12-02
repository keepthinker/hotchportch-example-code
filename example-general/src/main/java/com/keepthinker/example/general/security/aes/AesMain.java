package com.keepthinker.example.general.security.aes;

import com.keepthinker.example.general.security.EncodeType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AesMain {
    public static void main(String[] args) throws Exception {
        int threads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        EncodeType encodeType = EncodeType.BASE64;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 200; j++) {
                executorService.execute(() -> {
                    try {
                        String secretKey = AesUtils.generateKey(encodeType);
                        byte[] encryptedData = AesUtils.encrypt(encodeType, secretKey, "hello world!".getBytes());
                        System.out.println(new String(AesUtils.decrypt(encodeType, secretKey, encryptedData)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
		executorService.shutdown();
    }

}
