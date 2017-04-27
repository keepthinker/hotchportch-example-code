package com.keepthinker.example.general.localization;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.keepthinker.example.general.SystemUtils.print;

public class Main {
	private static final String LSTRING_FILE = "com.keepthinker.example.general.localization.LocalStrings";
	public static void main(String[] args){
		ResourceBundle bundle = ResourceBundle.getBundle(LSTRING_FILE);
		print(bundle.getString("name"));

		ResourceBundle bundleUS = ResourceBundle.getBundle(LSTRING_FILE, Locale.US);
		print(bundleUS.getString("name"));

		print("\nLocale.CHINA 部分信息：");
		print(Locale.CHINA.getCountry());
		print(Locale.CHINA.getDisplayCountry());
		print(Locale.CHINA.getDisplayName());
		print(Locale.CHINA.getDisplayLanguage());
		print(Locale.CHINA.getDisplayScript());
		print(Locale.CHINA.getDisplayVariant());
		print(Locale.CHINA.getISO3Country());
		print(Locale.CHINA.toLanguageTag());
		
	}
}
