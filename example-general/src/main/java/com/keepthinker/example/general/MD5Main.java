package com.keepthinker.example.general;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MD5Main {
	public static void main(String[] args) throws Exception{
		System.out.println(generateMd5FromFile("/home/keshengkai/workspace/example/example-general/createworkbook.xlsx"));
	}

	public static String generateMd5FromFile(String filePath){
		FileInputStream fis = null;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(filePath);
			byte[] dataBytes = new byte[1024];
			int nread = 0;

			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			};

			byte[] mdbytes = md.digest();

			//convert the byte to hex format
			String hexFormatResult = toHexFormat(mdbytes);
			fis.close();
			return hexFormatResult;
		}catch(Exception e){
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e1) {
				}
			}
			return null;
		}
	}
	
	public static String toHexFormat(byte[] bytes){
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
