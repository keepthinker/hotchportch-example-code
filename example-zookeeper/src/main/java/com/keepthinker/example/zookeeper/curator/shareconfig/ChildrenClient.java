package com.keepthinker.example.zookeeper.curator.shareconfig;

import com.keepthinker.example.zookeeper.curator.framework.CreateClientExamples;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

import java.util.List;

public class ChildrenClient implements Runnable{

	protected long defaultSleepTime = 2000;
	private List<String> children = null;
	private CuratorFramework curatorFramework = CreateClientExamples.createSimple(ShareConfigConstant.DEFAULT_CONNECTION_STRING);
	public ChildrenClient(){
		curatorFramework.start();
	}
	public void run(){
		try {
			children = getChildrenAndWatch();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		service();

	}

	private List<String> getChildrenAndWatch() throws Exception{
		System.out.println("get children and watch");
		List<String> strs= curatorFramework.getChildren().usingWatcher(new ChildrenChangeWatcher()).forPath(ShareConfigConstant.DEFAULT_NODE_PATH);
		return strs;
	}

	private void service(){
		for(;;){
			try {
				Thread.sleep(defaultSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(String str : children){
				System.out.println(str);
			}
		}
	}

	private class ChildrenChangeWatcher implements Watcher{

		@Override
		public void process(WatchedEvent event) {
			try {
				if(event.getType() == EventType.NodeChildrenChanged){
					System.out.println("NodeChildrenChanged");
					children = getChildrenAndWatch();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
