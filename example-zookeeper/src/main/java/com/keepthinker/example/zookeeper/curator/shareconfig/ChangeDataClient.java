package com.keepthinker.example.zookeeper.curator.shareconfig;

import com.keepthinker.example.zookeeper.curator.framework.CreateClientExamples;
import org.apache.curator.framework.CuratorFramework;

public class ChangeDataClient implements Runnable{
	
	private CuratorFramework curatorFramework = CreateClientExamples.createSimple(ShareConfigConstant.DEFAULT_CONNECTION_STRING);

	public ChangeDataClient(){
		curatorFramework.start();
	}
	public void run(){
		try {
			curatorFramework.setData().forPath(ShareConfigConstant.DEFAULT_NODE_PATH, "ChangeDataClient".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
