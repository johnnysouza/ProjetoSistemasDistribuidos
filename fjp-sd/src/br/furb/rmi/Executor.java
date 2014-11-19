package br.furb.rmi;

import java.rmi.Remote;

public interface Executor extends Remote {
	
	public int execute(String classpath, String mainClass);

}
