package br.furb.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.furb.builder.BuilderImpl;

public class BuilderServer extends UnicastRemoteObject implements Builder {

	private static final long serialVersionUID = 1L;
	
	protected BuilderServer() throws RemoteException {
		super();
	}		
	
	@Override
	public boolean compile(String jobName, String projectDir) throws RemoteException {
		Builder builder = new BuilderImpl();
		return builder.compile(jobName, projectDir);		
	}

	@Override
	public boolean test(String jobName, String projectDir) throws RemoteException {
		Builder builder = new BuilderImpl();
		return builder.test(jobName, projectDir);
	}

	@Override
	public boolean packageJar(String jobName, String projectDir) throws RemoteException {
		Builder builder = new BuilderImpl();
		return builder.packageJar(jobName, projectDir);
	}
	
	public static void main(String[] args) {
		BuilderServer obj;
		try {
			obj = new BuilderServer();
			Naming.rebind("//localhost/builder", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
