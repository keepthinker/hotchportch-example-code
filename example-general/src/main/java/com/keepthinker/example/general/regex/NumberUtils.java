package com.keepthinker.example.general.regex;

import static com.keepthinker.example.general.SystemUtils.*;

public class NumberUtils{
	private static String numberRegex = "(-|\\+)?\\d+e?\\d+";
	
	public static void printIsNumber(){
		print("-123128".matches(numberRegex));
		print("+123128".matches(numberRegex));
		print("123128".matches(numberRegex));
		print("1233e28".matches(numberRegex));
		print("+1233e28".matches(numberRegex));
		print("-1233e28".matches(numberRegex));
	}
	
	public static void printIsNumber(String num){
		print(num.matches(numberRegex));
	}

	public static void printIsNumber(String numberRegex, String num){
		print("-123128".matches(numberRegex));
		print("+123128".matches(numberRegex));
		print("123128".matches(numberRegex));
		print(" 123128".matches(numberRegex));
		print("1233e28".matches(numberRegex));
		print("+1233e28".matches(numberRegex));
		print("-1233e28".matches(numberRegex));
	}
}
