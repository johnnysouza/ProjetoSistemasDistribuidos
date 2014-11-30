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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((repositoryPath == null) ? 0 : repositoryPath.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (repositoryPath == null) {
			if (other.repositoryPath != null)
				return false;
		} else if (!repositoryPath.equals(other.repositoryPath))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Job [name=" + name + ", type=" + type + ", repositoryPath=" + repositoryPath + "]";
	}

}
