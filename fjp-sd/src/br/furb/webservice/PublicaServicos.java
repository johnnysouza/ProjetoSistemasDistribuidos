package br.furb.webservice;

import javax.xml.ws.Endpoint;

public class PublicaServicos {
	
	public static void main(String[] args) {
		GitCloneWeb service = new GitCloneWeb();
		Endpoint endpoint = Endpoint.publish("http://localhost:9090/clone", service);
	}

}
