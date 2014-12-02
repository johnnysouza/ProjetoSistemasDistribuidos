package br.furb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Builder extends Remote {
	
	public boolean compile(String jobName, String projectDir) throws RemoteException;	
	public boolean test(String jobName, String projectDir) throws RemoteException;
	public boolean packageJar(String jobName, String projectDir) throws RemoteException;
	
	

}
