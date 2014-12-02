package br.furb.git;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GitConfig {
	
	private static GitConfig config;
	private Properties properties;
	
	private GitConfig() {
		properties = new Properties();
	}
	
	private void load() throws IOException {
		File file = new File("git.properties");
		InputStream input = new FileInputStream(file);
		properties.load(input);
	}
	
	public static GitConfig getInstance() {
		if (config == null) {
			config = new GitConfig();
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
		return properties.getProperty(GitProperties.GIT_CMD.getProperty());
	}

}
