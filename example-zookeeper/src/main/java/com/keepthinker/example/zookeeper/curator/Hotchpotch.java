package com.keepthinker.example.zookeeper.curator;

import com.keepthinker.example.zookeeper.Constant;
import com.keepthinker.example.zookeeper.curator.framework.CreateClientExamples;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

import java.util.Date;

public class Hotchpotch {

	public static void main(String[] args) {
		CuratorFramework curatorFramework = CreateClientExamples.createSimple(Constant.DEFAULT_CONNECTION_STRING);
		try {
			curatorFramework.start();
			Stat stat = curatorFramework.checkExists().forPath(Constant.DEFAULT_NODE_PATH);
			if(stat == null){
				System.out.println("Not existed, create a new one named " + Constant.DEFAULT_NODE_PATH);
				String result = curatorFramework.create().forPath("/MyFirstZnode", "payload".getBytes());
				System.out.println("result: " + result);
			}else{
				System.out.println("existed");
				System.out.format("version: %10d, createTime: %10s\n", stat.getVersion(), new Date(stat.getCtime()).toString());
				String data = new String(curatorFramework.getData().forPath(Constant.DEFAULT_NODE_PATH));
				System.out.format("data: %s",data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
