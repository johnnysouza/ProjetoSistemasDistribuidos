package br.furb.corba.configuration;


public interface JobManagerDao {
	
	void save(Job job);
	boolean exist(String jobName);
	void delete(String jobName);
	Job load(String path);
	Job[] loadAll();

}
