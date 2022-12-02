package com.keepthinker.example.general.security.rsa;

import com.keepthinker.example.general.security.EncodeType;
import com.keepthinker.example.general.security.StringKeyPair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RsaMain {

    public static void main(String[] args) throws Exception {
        int threads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        EncodeType encodeType = EncodeType.HEX;
        StringKeyPair keyPair = RsaUtils.generateKeyPair(encodeType);
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    try {
						byte[] encryptedData = RsaUtils.encrypt(encodeType, keyPair.getPublicKey(), "hell world!".getBytes());
                        byte[] decryptedData = RsaUtils.decrypt(encodeType, keyPair.getPrivateKey(), encryptedData);
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
