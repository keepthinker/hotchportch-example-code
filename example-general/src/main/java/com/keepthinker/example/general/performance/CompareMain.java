package com.keepthinker.example.general.performance;

public class CompareMain {
	public static void main(String[] args) throws Exception {
//		Thread.sleep(1000);
		boolean b = false;
		long timePoint0 = System.currentTimeMillis();
		for(long i = 0, len = Integer.MAX_VALUE * 10L; i < len; i++){
			b = (1 > 0 && 1 > 0 && 1 > 0);
		}
		long timePoint1 = System.currentTimeMillis();
		System.out.println(timePoint1 - timePoint0);
		System.out.println(b);
		b = false;
		long timePoint2 = System.currentTimeMillis();
		for(long i = 0, len = Integer.MAX_VALUE * 10L; i < len; i++){
			b = (1 >= 0 && 1 >= 0 && 1 >= 0);
		}
		long timePoint3 = System.currentTimeMillis();
		System.out.println(timePoint3 - timePoint2);
		System.out.println(b);
		
		}
}
