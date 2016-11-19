package com.keepthinker.example.general.security;

public class FormatUtils {
	public static String toHexFormat(byte[] bytes){
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
