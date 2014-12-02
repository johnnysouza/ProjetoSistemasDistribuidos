package br.furb.webservice;

import javax.xml.ws.Endpoint;

public class PublicaServicos {
	
	public static void main(String[] args) {
		GitCloneWeb gitCloneService = new GitCloneWeb();
		LoginWeb loginService = new LoginWeb();
		
		Endpoint.publish("http://localhost:9090/clone", gitCloneService);
		Endpoint.publish("http://localhost:9090/login", loginService);
	}

}
