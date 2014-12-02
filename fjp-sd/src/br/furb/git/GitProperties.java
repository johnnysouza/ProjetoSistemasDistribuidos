package br.furb.git;

public enum GitProperties {
	
	GIT_CMD("gitcmd");
	
	public String property;
	
	GitProperties(String property) {
		this.property = property;
	}
	
	public String getProperty() {
		return property;		
	}

}
