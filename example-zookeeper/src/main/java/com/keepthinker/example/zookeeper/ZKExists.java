package com.keepthinker.example.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZKExists {
	   private static ZooKeeper zk;
	   private static ZooKeeperConnection conn;

	   private static CountDownLatch connectedSignal = new CountDownLatch(1);

	   // Method to check existence of znode and its status, if znode is available.
	   public static Stat znode_exists(String path) throws
	      KeeperException,InterruptedException {
	      return zk.exists(path, new Watcher(){

			@Override
			public void process(WatchedEvent event) {
				System.out.format("zookeeper exists event : %s\n", event);
				connectedSignal.countDown();
			}});
	      
	   }

	   public static void main(String[] args) throws InterruptedException,KeeperException {
	      String path = "/MyFirstZnode"; // Assign znode to the specified path
				
	      try {
	         conn = new ZooKeeperConnection();
	         zk = conn.connect("localhost");
	         Stat stat = znode_exists(path); // Stat checks the path of the znode
					
	         if(stat != null) {
	            System.out.println("Node exists and the node version is " +
	            stat.getVersion());
	         } else {
	            System.out.println("Node does not exists");
	         }
	         
		      connectedSignal.await();
					
	      } catch(Exception e) {
	         System.out.println(e.getMessage()); // Catches error messages
	      }
	   }
	}