package com.keepthinker.example.general.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerIntf extends Remote {
    public String getMessage() throws RemoteException;
    public TransferObject getTransferObject() throws RemoteException;
}