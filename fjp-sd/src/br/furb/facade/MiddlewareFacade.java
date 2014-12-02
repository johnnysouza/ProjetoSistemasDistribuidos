package br.furb.facade;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import br.furb.config.ConfigHelper;
import br.furb.config.ConfigProperties;
import br.furb.corba.JobManagerClient;
import br.furb.corba.configuration.Job;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.rmi.Builder;
import br.furb.rmi.BuilderClient;
import br.furb.webservice.client.GitCloneWeb;
import br.furb.webservice.client.GitCloneWebService;
import br.furb.webservice.client.LoginWeb;
import br.furb.webservice.client.LoginWebService;

public class MiddlewareFacade {

	private static MiddlewareFacade instance;
	private List<String> serversWebService;
	private List<String> serversCorba;
	private List<String> serversRmi;
	private String webServiceServerPort;
	Integer indexWebService;
	Integer indexCorba;
	Integer indexRmi;

	private MiddlewareFacade() {
		serversWebService = new ArrayList<String>();
		loadServers(serversWebService, ProtocolType.WEBSERVICES);
		serversCorba = new ArrayList<String>();
		loadServers(serversCorba, ProtocolType.CORBA);
		serversRmi = new ArrayList<String>();
		loadServers(serversRmi, ProtocolType.RMI);

		webServiceServerPort = ConfigHelper.getInstance()
				.getWebServiceServerPort();
		indexWebService = 0;
		indexCorba = 0;
		indexRmi = 0;
	}

	private void loadServers(List<String> servers, ProtocolType type) {
		loadServers(servers, ConfigProperties.SERVERS.getProperty());

		switch (type) {
		case CORBA:
			loadServers(servers, ConfigProperties.SERVERS_CORBA.getProperty());
			break;
		case RMI:
			loadServers(servers, ConfigProperties.SERVERS_RMI.getProperty());
			break;
		case WEBSERVICES:
			loadServers(servers,
					ConfigProperties.SERVERS_WEBSERVICES.getProperty());
			break;
		}
	}

	private void loadServers(List<String> servers, String key) {
		if (key != null) {
			String value = ConfigHelper.getInstance().getString(key);

			if (value != null) {
				String[] strings = value.split(",");

				for (String server : strings) {
					if (!servers.contains(server)) {
						servers.add(server);
					}
				}
			}
		}
	}

	public static MiddlewareFacade getInstance() {
		if (instance == null) {
			instance = new MiddlewareFacade();
		}

		return instance;
	}

	private String getServer(List<String> servers, Integer index) {
		if (index >= servers.size()) {
			index = 0;
		}

		return servers.get(index++);
	}

	private String getAvailableServer(ProtocolType type) {
		switch (type) {
		case CORBA:
			if (indexCorba >= serversCorba.size()) {
				indexCorba = 0;
			}

			return serversCorba.get(indexCorba++);			
		case RMI:
			if (indexRmi >= serversRmi.size()) {
				indexRmi = 0;
			}

			return serversRmi.get(indexRmi++);
		case WEBSERVICES:
			if (indexWebService >= serversWebService.size()) {
				indexWebService = 0;
			}

			return serversWebService.get(indexWebService++);
		}

		return null;
	}

	public boolean compile(String jobName, String projectDir)
			throws RemoteException {
		Builder builder = BuilderClient
				.createBuilder(getAvailableServer(ProtocolType.RMI));
		return builder.compile(jobName, projectDir);
	}

	public boolean test(String jobName, String projectDir)
			throws RemoteException {
		Builder builder = BuilderClient
				.createBuilder(getAvailableServer(ProtocolType.RMI));
		return builder.test(jobName, projectDir);
	}

	public boolean packageJar(String jobName, String projectDir)
			throws RemoteException {
		Builder builder = BuilderClient
				.createBuilder(getAvailableServer(ProtocolType.RMI));
		return builder.packageJar(jobName, projectDir);
	}

	private URL makeWSURL(String service) {
		try {
			return new URL("http://"
					+ getAvailableServer(ProtocolType.WEBSERVICES) + ":"
					+ webServiceServerPort + "/" + service + "?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean login(String userName, String password) {
		LoginWeb login = new LoginWebService(makeWSURL("login"))
				.getLoginWebPort();
		return login.login(userName, password);
	}

	public boolean cloneRepository(String jobName, String repository,
			String targetDir) {
		GitCloneWeb clone = new GitCloneWebService(makeWSURL("clone"))
				.getGitCloneWebPort();
		return clone.cloneRepository(jobName, repository, targetDir);
	}

	private String[] makeORBParams() {
		return new String[] { "-ORBInitialHost",
				getAvailableServer(ProtocolType.CORBA) };
	}

	public void addHistory(String jobName, JobHistory history) {
		JobManagerClient client = new JobManagerClient(makeORBParams());
		client.addHistory(jobName, history);
	}

	public JobHistory[] loadHistorys(String jobName) {
		JobManagerClient client = new JobManagerClient(makeORBParams());
		return client.loadHistorys(jobName);
	}

	public Job[] loadAll() {
		JobManagerClient client = new JobManagerClient(makeORBParams());
		return client.loadAll();
	}

	public Job load(String jobName) {
		JobManagerClient client = new JobManagerClient(makeORBParams());
		return client.load(jobName);
	}

	public void delete(String jobName) {
		JobManagerClient client = new JobManagerClient(makeORBParams());
		client.delete(jobName);
	}

	public boolean exist(String jobName) {
		JobManagerClient client = new JobManagerClient(makeORBParams());
		return client.exist(jobName);
	}

	public void save(Job job) {
		JobManagerClient client = new JobManagerClient(makeORBParams());
		client.save(job);
	}

}
