package br.furb.corba.configuration;

import java.io.File;
import java.util.List;

public interface JobManagerDao {
	
	void save(Job job);
	boolean exist(String jobName);
	void delete(String jobName);
	Job load(File path);
	List<Job> loadAll();

}
