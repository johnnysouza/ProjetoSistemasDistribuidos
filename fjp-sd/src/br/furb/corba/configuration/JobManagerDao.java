package br.furb.corba.configuration;

import java.io.File;
import java.util.List;

public interface JobManagerDao {
	
	void save(String jobId);
	void delete(String jobId);
	Job load(File path);
	List<Job> loadAll();

}
