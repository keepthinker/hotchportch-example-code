package com.keepthinker.example.general.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keepthinker.example.general.security.EncodeType;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

import java.text.SimpleDateFormat;

public class Utils {

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static ClassLoader getContextClassloader(){
		return Thread.currentThread().getContextClassLoader();
	}
	private static String path  = getContextClassloader().getResource("").getPath();
	public static String getContextClasspath(){
		return path;
	}

	public static String toJsonString(Object obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			return "";
		}
	}

	public static byte[] decode(EncodeType keyEncodeType, String data) throws Exception {
		switch (keyEncodeType){
			case HEX:
				return Hex.decodeHex(data.toCharArray());
			case BASE64:
				return Base64.getDecoder().decode(data);
			case BASE64_MIME:
				return Base64.getMimeDecoder().decode(data);
			case BASE64_URL_SAFE:
				return Base64.getUrlDecoder().decode(data);
		}
		return null;
	}

	public static String encode(EncodeType keyEncodeType, byte[] data) throws Exception {
		switch (keyEncodeType){
			case HEX:
				return Hex.encodeHexString(data);
			case BASE64:
				return Base64.getEncoder().encodeToString(data);
			case BASE64_MIME:
				return Base64.getMimeEncoder().encodeToString(data);
			case BASE64_URL_SAFE:
				return Base64.getUrlEncoder().encodeToString(data);
		}
		return null;
	}

	public static String urlEncode(String str, String charSet) {
		try {
			return URLEncoder.encode(str, charSet);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String urlDecode(String str, String charSet) {
		try {
			return URLDecoder.decode(str, charSet);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
