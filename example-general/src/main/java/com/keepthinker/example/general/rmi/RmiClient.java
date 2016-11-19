package com.keepthinker.example.general.rmi;
import java.rmi.Naming;

public class RmiClient { 
	public static void main(String args[]) throws Exception {
		RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
		System.out.println(obj.getMessage()); 
		System.out.println(obj.getTransferObject().getContent()); 
	}
}