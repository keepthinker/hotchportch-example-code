package com.keepthinker.example.general.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class RSAMain {

	public static void main(String[] args) throws Exception {
		//		KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
		//	    keyGenerator.init(8 * 4);
		//	    Key blowfishKey = keyGenerator.generateKey();
		long t0 = System.currentTimeMillis();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		long t1 = System.currentTimeMillis();
		System.out.println("Time consumption in : KeyPairGenerator.getInstance(\"RSA\") :" + (t1 - t0));
		keyPairGenerator.initialize(2 << 11);
		long t2 = System.currentTimeMillis();
		System.out.println("Time consumption in : keyPairGenerator.initialize : " + (t2 - t1));
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		long t3 = System.currentTimeMillis();
		System.out.println("Time consumption in : keyPairGenerator.genKeyPair() : " + (t3 - t2));
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		long t4 = System.currentTimeMillis();
		System.out.println("Time consumption in : Cipher.getInstance(\"RSA/ECB/PKCS1Padding\") : " + (t4 - t3));
		cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
		long t5 = System.currentTimeMillis();
		System.out.println("Time consumption in : cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic()) : " + (t5 - t4));
		System.out.print("public key : ");
		byte[] bytesPublic = keyPair.getPublic().getEncoded();
		System.out.println(FormatUtils.toHexFormat(bytesPublic));
		System.out.println(new String(keyPair.getPublic().getEncoded()));
		byte[] rawText = "f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607167c81f607c8df3fdba4c6bd0fca971f167c81f607c8df3fdba4c6bd0fca971f167c81f607c8df3fdba4c6bd0fca971f167c81f607c8df3fdba4c6bd0fca971f167c81f607c8df3fdba4c6bd0fca971f167c81f607c8df3fdba4c6bd0fca971f167c81f607c8df3fdba4c6bd0fca971f".getBytes();
		System.out.println("raw text :" + new String(rawText) + ": length = " + rawText.length);
		long t6 = System.currentTimeMillis();
		byte[] cipherText = cipher.doFinal(rawText);
		long t7 = System.currentTimeMillis();
		System.out.println("Time consumption in : cipher.doFinal(blowfishKeyBytes) encryt: " + (t7 - t6));
		System.out.println("cipherText : " + new String(cipherText));
		long t8 = System.currentTimeMillis();
		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
		long t9 = System.currentTimeMillis();
		System.out.println("Time consumption in : cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate()) : " + (t9 - t8));
		System.out.print("private key : ");
		System.out.println(FormatUtils.toHexFormat(keyPair.getPrivate().getEncoded()));

		long t10 = System.currentTimeMillis();
		byte[] decryptedKeyBytes = cipher.doFinal(cipherText);
		long t11 = System.currentTimeMillis();
		System.out.println("Time consumption in : cipher.doFinal(cipherText) decryt: " + (t11 - t10));
		System.out.println("decryptedKeyBytes : " + new String(decryptedKeyBytes));
		//	    SecretKey newBlowfishKey = new SecretKeySpec(decryptedKeyBytes, "Blowfish");
	}


}
