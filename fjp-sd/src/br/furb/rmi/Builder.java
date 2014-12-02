package br.furb.rmi;

import java.rmi.Remote;

public interface Builder extends Remote {
	
	public boolean compile(String jobName, String projectDir);	
	public boolean test(String jobName, String projectDir);
	public boolean packageJar(String jobName, String projectDir);
	
	

}
