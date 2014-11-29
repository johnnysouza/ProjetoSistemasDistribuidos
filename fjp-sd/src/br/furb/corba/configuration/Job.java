package br.furb.corba.configuration;

import java.io.Serializable;

public class Job implements Serializable {

	private static final long serialVersionUID = -1642612480582126889L;

	private String name;
	private JobType type;
	private String repositoryPath;
	
	public Job(String name, JobType type, String repositoryPath) {
		super();
		this.name = name;
		this.type = type;
		this.repositoryPath = repositoryPath;
	}

	public String getName() {
		return name;
	}

	public JobType getType() {
		return type;
	}

	public String getRepositoryPath() {
		return repositoryPath;
	}

}
