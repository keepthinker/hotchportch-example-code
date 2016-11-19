package com.keepthinker.example.designpattern.proxy;

import java.util.Date;
import java.util.Random;

public class SearchService implements Search{
	private Random ran = new Random(new Date().getTime());
	@Override
	public String search(String keyword) {
		ran.setSeed(new Date().getTime());
		return keyword + " : " + String.valueOf(ran.nextInt());
	}

}
