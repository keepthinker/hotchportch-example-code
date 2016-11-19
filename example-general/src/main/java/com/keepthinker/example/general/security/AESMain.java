package com.keepthinker.example.general.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESMain {
	public static void main(String[] args) throws Exception{
		KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
		KeyGen.init(128);

		SecretKey secKey = KeyGen.generateKey();

		Cipher aesCipher = Cipher.getInstance("AES");
		System.out.println("key : " + FormatUtils.toHexFormat(secKey.getEncoded()));

		byte[] byteText = "Your Plain Text Here".getBytes();

		aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
		byte[] encryptedData = aesCipher.doFinal(byteText);
		System.out.println("encryted data : " + FormatUtils.toHexFormat(encryptedData));

		aesCipher.init(Cipher.DECRYPT_MODE, secKey);
		byte[] decryptedData = aesCipher.doFinal(encryptedData);
		System.out.println(new String(decryptedData));
	}

}
