package com.keepthinker.example.general.format;

import java.text.*;
import java.util.Date;
import java.util.Locale;

public class FormatMain {
	public static void main(String[] args) throws Exception{
		dateFormat();
		messageFormat();
		decimalFormat();
	}

	private static void messageFormat(){
		int planet = 7;
		String event = "a disturbance in the Force";
		String result = MessageFormat.format(
				"At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
				planet, new Date(), event);
		System.out.println(result);


		int fileCount = 1273;
		String diskName = "MyDisk";
		Object[] testArgs = {new Long(fileCount), diskName};

		MessageFormat form = new MessageFormat(
				"The disk \"{1}\" contains {0} file(s).");
		System.out.println(form.format(testArgs));

		MessageFormat myform = new MessageFormat("I like {0} --1");
		System.out.println(myform.format(new Object[]{"Reading"}));

		//the bellowing is not good, because everytime it's invoked, it'll new a new MessageFormat object
		System.out.println(MessageFormat.format("I like {0}",
				"Reading --2"));
	}

	private static void dateFormat() throws ParseException{
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd");
		System.out.println(ft.format(dNow));

		System.out.println(ft.format(ft.parse("2015/02/01")));
	}

	private static void decimalFormat(){
		// Print out a number using the localized number, integer, currency,
		// and percent format for each locale
		Locale[] locales = NumberFormat.getAvailableLocales();
		double myNumber = -1234.56;
		NumberFormat form;
		for (int j=0; j<4; ++j) {
			System.out.println("FORMAT");
			for (int i = 0; i < locales.length; ++i) {
				if (locales[i].getCountry().length() == 0) {
					continue; // Skip language-only locales
				}
				System.out.print(locales[i].getDisplayName());
				switch (j) {
				case 0:
					form = NumberFormat.getInstance(locales[i]); break;
				case 1:
					form = NumberFormat.getIntegerInstance(locales[i]); break;
				case 2:
					form = NumberFormat.getCurrencyInstance(locales[i]); break;
				default:
					form = NumberFormat.getPercentInstance(locales[i]); break;
				}
				if (form instanceof DecimalFormat) {
					System.out.print(": " + ((DecimalFormat) form).toPattern());
				}
				System.out.print(" -> " + form.format(myNumber));
				try {
					System.out.println(" -> " + form.parse(form.format(myNumber)));
				} catch (ParseException e) {}
			}
		}
		
		double myInt = 123456.78;
		NumberFormat myForm = NumberFormat.getNumberInstance(Locale.SIMPLIFIED_CHINESE);
		System.out.println(myForm.format(myInt));
	}

}
