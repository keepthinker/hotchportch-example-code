package com.keepthinker.example.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author keshengkai
 */
public class HotchpotchMain {
	public static void main(String[] args){
		int[] appDetailIds ={1, 2};
		Integer[] objs = new Integer[appDetailIds.length];
		Arrays.asList(appDetailIds).toArray();
		System.out.println(appDetailIds.clone());
		new ArrayList<String>();

		AtomicReference<String> atomicReference = new AtomicReference<String>("a");
		atomicReference.compareAndSet("a", "b");
		System.out.println(atomicReference.get());

		AtomicInteger atomicInteger = new AtomicInteger(10);
		atomicInteger.getAndAdd(10);
		System.out.println(atomicInteger.get());
	}
}


