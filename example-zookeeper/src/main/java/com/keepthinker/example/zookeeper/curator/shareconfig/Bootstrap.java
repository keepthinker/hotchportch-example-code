package com.keepthinker.example.zookeeper.curator.shareconfig;

public class Bootstrap {

	public static void main(String[] args) {
		NodeChangeClient client = new NodeChangeClient();
		
		new Thread(client).start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ChangeDataClient dataClient = new ChangeDataClient();
		new Thread(dataClient).start();
		
		ChildrenClient childrenClient = new ChildrenClient();
		new Thread(childrenClient).start();
		
	}
}
