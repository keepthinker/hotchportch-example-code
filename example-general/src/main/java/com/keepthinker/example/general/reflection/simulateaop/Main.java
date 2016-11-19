package com.keepthinker.example.general.reflection.simulateaop;

import com.keepthinker.example.general.reflection.simulateaop.proxy.LightingProxyHelper;

public class Main {
	public static void main(String[] args) throws Exception{
		Sound thunder = LightingProxyHelper.lightingProxy(new ThunderService(), Sound.class);
		thunder.makeSound();
		
		Sound footrace = LightingProxyHelper.lightingProxy(new FireworkService(), Sound.class);
		footrace.makeSound();
		
		Reading reader = LightingProxyHelper.lightingProxy(new ReadingService(), Reading.class);
		reader.read();
	}
}
