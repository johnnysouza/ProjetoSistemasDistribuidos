package br.furb.facade;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import br.furb.config.ConfigHelper;
import br.furb.rmi.Builder;
import br.furb.rmi.BuilderClient;
import br.furb.webservice.client.GitCloneWeb;
import br.furb.webservice.client.GitCloneWebService;
import br.furb.webservice.client.LoginWeb;
import br.furb.webservice.client.LoginWebService;

public class MiddlewareFacade {
	
	
	private MiddlewareFacade instance;
	private String[] servers;
	private String webServiceServerPort;
	int index;
	
	private MiddlewareFacade() {
		servers = ConfigHelper.getInstance().getServers().split(",");
		webServiceServerPort = ConfigHelper.getInstance().getWebServiceServerPort();
		index = 0;
	}
	
	public MiddlewareFacade getInstance() {
		if (instance == null) {
			instance = new MiddlewareFacade();
		}
		
		return instance;
	}
	
	private String getAvailableServer() {
		if (index >= servers.length) {
			index = 0;
		}
		
		return servers[index++];			
	}

	public boolean compile(String jobName, String projectDir) throws RemoteException {
		Builder builder = BuilderClient.createBuilder(getAvailableServer());
		return builder.compile(jobName, projectDir);
	}

	public boolean test(String jobName, String projectDir) throws RemoteException {
		Builder builder = BuilderClient.createBuilder(getAvailableServer());
		return builder.test(jobName, projectDir);
	}

	public boolean packageJar(String jobName, String projectDir) throws RemoteException {
		Builder builder = BuilderClient.createBuilder(getAvailableServer());
		return builder.packageJar(jobName, projectDir);
	}
	
	private URL makeWSURL(String service) {
		try {
			return new URL("http://" + getAvailableServer() + ":" + webServiceServerPort + "/" + service + "?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean login(String userName, String password) {
		LoginWeb login = new LoginWebService(makeWSURL("login")).getLoginWebPort();
		return login.login(userName, password);
	}
	
	public boolean cloneRepository(String jobName, String repository, String targetDir) {
		GitCloneWeb clone = new GitCloneWebService(makeWSURL("clone")).getGitCloneWebPort();
		return clone.cloneRepository(jobName, repository, targetDir);
	}

}
