package br.furb.rmi;

import java.rmi.Naming;

public class ExecutorClient {

	public static void main(String[] args) {
		try { 
			Executor obj = (Executor) Naming.lookup("//" + args[0] + "/execute"); 
			int message = obj.execute(args[1], args[2]); 
			System.out.println("Resposta no servidor RMI de: \"" + message + "\""); 
		} catch (Exception e) { 
			System.out.println("ExecutorClient exception: " + e.getMessage()); e.printStackTrace(); 
		} 		
	}
}
