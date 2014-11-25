package br.furb.corba.configuration;

import java.io.Serializable;

public class Job implements Serializable{
	
	private static final long serialVersionUID = -1642612480582126889L;
	
	private int id;
	private String name;
	private JobType type;
	private String repositoryPath;
	

}
