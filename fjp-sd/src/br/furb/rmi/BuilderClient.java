package br.furb.rmi;

import java.rmi.Naming;

public class BuilderClient {
	
	public static Builder createBuilder(String server) {
		try {
			return (Builder) Naming.lookup("//" + server + "/builder");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
