package com.keepthinker.example.general.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {

	private static final long serialVersionUID = -8282292216466065000L;
	public static final String MESSAGE = "Hello World";

	public RmiServer() throws RemoteException {
		super(0);    // required to avoid the 'rmic' step, see below
	}

	public String getMessage() {
		return MESSAGE;
	}

	public static void main(String args[]) throws Exception {
		System.out.println("RMI server started");

		try { //special exception handler for registry creation
			LocateRegistry.createRegistry(1099); 
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			//do nothing, error means registry already exists
			System.out.println("java RMI registry already exists.");
		}

		//Instantiate RmiServer

		RmiServer obj = new RmiServer();

		// Bind this object instance to the name "RmiServer"
		Naming.rebind("//localhost/RmiServer", obj);
		System.out.println("PeerServer bound in registry");
	}

	@Override
	public TransferObject getTransferObject() {
		TransferObject to = new TransferObject();
		to.setId(1);
		to.setContent("msg");
		return to;
	}
}
