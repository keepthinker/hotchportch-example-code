package com.keepthinker.example.zookeeper.curator.shareconfig;


import com.keepthinker.example.zookeeper.curator.framework.CreateClientExamples;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;

public class NodeChangeClient implements Runnable{
	protected long defaultSleepTime = 2000;
	private String config = "default";
	private CuratorFramework curatorFramework = CreateClientExamples.createSimple(ShareConfigConstant.DEFAULT_CONNECTION_STRING);
	public NodeChangeClient(){
		curatorFramework.start();
	}
	public void run(){
		try {
			Stat stat = curatorFramework.checkExists().forPath(ShareConfigConstant.DEFAULT_NODE_PATH);
			if(stat == null){
				curatorFramework.create().forPath(ShareConfigConstant.DEFAULT_NODE_PATH, "config1".getBytes());
				config = new String(getAndWatch());
			}else{
				config = new String(getAndWatch());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		service();

	}

	private byte[] getAndWatch() throws Exception{
		System.out.println("get and watch");
		return curatorFramework.getData().usingWatcher(new ConfigChangeWatcher()).forPath(ShareConfigConstant.DEFAULT_NODE_PATH);
	}

	private void service(){
		for(;;){
			try {
				Thread.sleep(defaultSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(config);
		}
	}

	private class ConfigChangeWatcher implements Watcher{

		@Override
		public void process(WatchedEvent event) {
			try {
				if(event.getType() == EventType.NodeDataChanged){
					System.out.println("NodeDataChanged");
					config = new String(getAndWatch());
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
