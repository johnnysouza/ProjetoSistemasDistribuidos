package br.furb.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper {
	
	private static ConfigHelper config;
	private Properties properties;
	
	private ConfigHelper() {
		properties = new Properties();
	}
	
	private void load() throws IOException {
		File file = new File("fjp.properties");
		InputStream input = new FileInputStream(file);
		properties.load(input);
	}
	
	public static ConfigHelper getInstance() {
		if (config == null) {
			config = new ConfigHelper();
			try {
				config.load();
			} catch (IOException e) {				
				e.printStackTrace();
				config = null;
			}
		}
		
		return config;
	}
	
	public String getGitCmd() {
		return properties.getProperty(ConfigProperties.GIT_CMD.getProperty());
	}
	
	public String getMvnCmd() {
		return properties.getProperty(ConfigProperties.MVN_CMD.getProperty());
	}
	
	public String getServers() {
		return properties.getProperty(ConfigProperties.SERVERS.getProperty());
	}
	
	public String getServersWebServices() {
		return properties.getProperty(ConfigProperties.SERVERS_WEBSERVICES.getProperty());
	}
	
	public String getServersCorba() {
		return properties.getProperty(ConfigProperties.SERVERS_CORBA.getProperty());
	}
	
	public String getServersRMI() {
		return properties.getProperty(ConfigProperties.SERVERS_RMI.getProperty());
	}
	
	public String getString(String key) {
		return properties.getProperty(key);
	}
	
	public String getWebServiceServerPort() {
		return properties.getProperty(ConfigProperties.WEBSERVICE_SERVER_PORT.getProperty());
	}	

}
