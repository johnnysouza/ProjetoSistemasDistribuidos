package br.furb.config;

public enum ConfigProperties {
	
	GIT_CMD("gitcmd"),
	MVN_CMD("mvncmd"),
	SERVERS("servers");
	
	
	public String property;
	
	ConfigProperties(String property) {
		this.property = property;
	}
	
	public String getProperty() {
		return property;		
	}

}
