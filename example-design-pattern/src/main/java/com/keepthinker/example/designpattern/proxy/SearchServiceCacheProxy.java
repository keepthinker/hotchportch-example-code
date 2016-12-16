package com.keepthinker.example.designpattern.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SearchServiceCacheProxy implements Search{

	private Search searchService = new SearchService();
	private Map<String, Object> cache = new HashMap<String, Object>();
	@Override
	public String search(String keyword) {
		if(cache.containsKey(keyword)){
			return (String) cache.get(keyword);
		}
		String value = searchService.search(keyword);
		cache.put(keyword, value);
		return value;
	}

	public SearchServiceCacheProxy(){
		this.period = 10;
		clearingDeamon();
	}

	/**
	 * 秒数
	 * @param period
	 */
	public SearchServiceCacheProxy(int period){
		this.period = period;
		clearingDeamon();
	}

	private int period;

	public void setPeriod(int period) {
		this.period = period;
	}

	private void clearingDeamon(){
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cache.clear();
				System.out.println("Cache1 is cleared");
			}
		};
		timer.schedule(task, 0, 1000 * period);
	}

}
