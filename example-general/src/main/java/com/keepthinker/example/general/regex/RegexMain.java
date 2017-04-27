package com.keepthinker.example.general.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.keepthinker.example.general.SystemUtils.print;

public class RegexMain {
	public static void main(String[] args){
		example3();
	}
	
	private static void example3(){
		String str = "a = 2 and b = 32 order   by a   , b";
		print("reluctant:");
		matchState("\\s*,\\s*", str);
		for(String sub :str.split("\\s*,\\s*")){
			System.out.println(sub + " end");
		};
	}
	
	private static void example2(){
		String str = "a = 2 and b = 32 order   by a, b";
		print("reluctant:");
		matchState("order\\s*by", str);
		str = "a = 2 and b = 32";
		print("reluctant:");
		matchState("order\\s*by", str);
		str = "order   by a, b";
		print("reluctant:");
		matchState("order\\s*by", str);
	}
	
	private static void example1(){
		NumberUtils.printIsNumber("-3123ee19238");
		NumberUtils.printIsNumber("-3123e1923e8");
		NumberUtils.printIsNumber("-e3123e19238");
		NumberUtils.printIsNumber("-3123e19238");
		NumberUtils.printIsNumber("+3123e19238");
		NumberUtils.printIsNumber("3123e19238");

		print();
		print("12sd1.xml : " + "12sd1.xml".matches("\\w+.xml"));
		print("12a2_sd1.xml : " + "12a2_sd1.xml".matches("[a-zA-Z_0-9]+.xml"));
		print(".xml : " + ".xml".matches("[a-zA-Z_0-9]+.xml"));

		print("asdfs1211.xml1211.xml".split("1+?.xml"));

		String str = "fofofofoofofasdfsaffoa";
		print("greedy:");
		matchState(".*fo", str);

		print("reluctant:");
		matchState(".*?fo", str);

		str = "ffofofofofofo";
		print("match end:");
		matchState("^f.*fo$", str);
	}
	

	private static void matchState(String regex, String msg){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(msg);
		while(m.find()){
			print("group:" + m.group() + "----- start:" + m.start() + " ----- end:" + m.end());
		}
	}

}
