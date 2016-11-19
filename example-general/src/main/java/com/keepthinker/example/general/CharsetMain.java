package com.keepthinker.example.general;

import java.nio.charset.Charset;
import org.apache.log4j.Logger;

public class CharsetMain {
	private static Logger logger = Logger.getLogger(CharsetMain.class);

	public static void main( String[] args ){
		logger.info( "Hello World!" );
		chinese();
		english();
		charset();
	}
	private static void chinese(){
		String[] chineseArr =  new String[]{"你好", "发锁", "斯蒂芬个人", "阿斯顿方次", "沃尔 沃", "次"};
		analyzeString(chineseArr);
	}

	private static void english(){

		String[] englishArr =  new String[]{"as", "gag", "asce", "agr23", "asd df4", "a"};
		analyzeString(englishArr);
	}
	
	private static void charset(){
		for(String charset : Charset.availableCharsets().keySet()){
			System.out.println(charset + " : " + Charset.availableCharsets().get(charset));
		}
	}
	
	private static void analyzeString(String... strArr){
		StringBuilder result = new StringBuilder();
		for(int i = 0, leni = strArr.length; i < leni; i++){
			result.append('\n').append(strArr[i]).append("\nstring length: ").append(strArr[i].length()).append('\n');

			byte[] bytes = null;
			try {
				bytes = strArr[i].getBytes();

				result.append("bytes length: ").append(bytes.length).append('\n');
				for(int j = 0, lenj = bytes.length; j < lenj ; j++){
					result.append(bytes[j]).append(' ');
				}
				result.append("\ncode: ");
				for(int j = 0, lenj = strArr[i].length(); j < lenj ; j++){
					result.append(strArr[i].codePointAt(j)).append(' ');
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			result.append('\n');
		}
		logger.info(result.toString());
	}

}
