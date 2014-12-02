package br.furb.rmi;

import java.rmi.Remote;

public interface Builder extends Remote {
	
	public boolean compile(String projectDir);
	
	public boolean test(String projectDir);
	
	

}
