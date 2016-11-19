package com.keepthinker.example.general.performance;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PerformanceMain {
	private static Logger logger = Logger.getLogger(PerformanceMain.class);
	private static final int CONFIG_SIZE = 200;
	private List<Config>  configs = new ArrayList<Config>();
	private static final int SITUATION_SIZE = 2 >> 4;
	public static void main(String[] args){
		PerformanceMain  performanceMain = new PerformanceMain();
		long st = System.nanoTime();
		performanceMain.test();
		long en = System.nanoTime();
		logger.info(en - st);
	}
	
	public PerformanceMain(){
		Config config = null;
		for(int i = 0 ; i < CONFIG_SIZE >> 2; i++){
			config = new Config();
			config.setA("");
			config.setD("q12");
			configs.add(config);
		}

		for(int i = 0 ; i < CONFIG_SIZE >> 2; i++){
			config = new Config();
			config.setA("");
			config.setC("q12");
			configs.add(config);
		}
		

		for(int i = 0 ; i < CONFIG_SIZE >> 2; i++){
			config = new Config();
			config.setA("");
			config.setB("q12");
			config.setD("qsd2");
			configs.add(config);
		}

		for(int i = 0 ; i < CONFIG_SIZE >> 2; i++){
			config = new Config();
			config.setD("");
			config.setB("q12");
			configs.add(config);
		}
		logger.info("config size : " + configs.size());
		
	}
	
	public void test(){
		getSituations(configs);
	}
	
	public boolean[] getSituations(List<Config> configs){
		boolean[] situations = new boolean[SITUATION_SIZE];
		for(int i = 0; i < CONFIG_SIZE; i++){
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
			if(isStringEmpty(configs.get(i).getA())
					&& isStringEmpty(configs.get(i).getB())
					&& isStringEmpty(configs.get(i).getC())
					&& isStringEmpty(configs.get(i).getD())){
				situations[0] =true;
			}
		}
		return situations;
	}
	
	public boolean isStringEmpty(String str){
		return str == null || str.equals("");
	}
}

class Config{
	private String a;
	private String b;
	private String c;
	private String d;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	
	
}
