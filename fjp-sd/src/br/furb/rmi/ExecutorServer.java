package br.furb.rmi;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.furb.executor.ExecutorImpl;

public class ExecutorServer extends UnicastRemoteObject implements Executor {

	private static final long serialVersionUID = 1L;
	
	public ExecutorServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) {
		try {
			ExecutorServer obj = new ExecutorServer();
			Naming.rebind("//localhost/HelloWorld", obj);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}		
	}

	@Override
	public int execute(String classpath, String mainClass) {
		System.out.println(String.format("execute(classpath: %s / mainClass: %s)", classpath, mainClass));
		ExecutorImpl executor = new ExecutorImpl(classpath, mainClass);
		try {
			return executor.execute();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	

	
	
	
	
	

}
