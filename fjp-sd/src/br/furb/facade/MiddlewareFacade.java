package br.furb.facade;

import br.furb.config.ConfigHelper;

public class MiddlewareFacade {
	
	private String[] servers;
	
	public MiddlewareFacade() {
		servers = ConfigHelper.getInstance().getServers().split(",");
	}

	public boolean compile(String jobName, String projectDir) {
		return false;
	}

	public boolean test(String jobName, String projectDir) {
		return false;
	}

	public boolean packageJar(String jobName, String projectDir) {
		return false;
	}
	
	public boolean login(String userName, String password) {
		return false;
	}
	
	public boolean cloneRepository(String jobName, String repository, String targetDir) {
		return false;
	}

}
