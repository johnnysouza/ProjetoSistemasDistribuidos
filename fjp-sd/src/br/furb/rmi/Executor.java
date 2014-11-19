package br.furb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Executor extends Remote {
	
	public int execute(String classpath, String mainClass) throws RemoteException;
	
	public int execute(String classpath, String mainClass, String[] params) throws RemoteException;

}
