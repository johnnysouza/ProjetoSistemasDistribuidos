package br.furb.corba.configuration;


public interface JobConfigurationDao {
	
	void save(Job job);
	boolean exist(String jobName);
	void delete(String jobName);
	Job load(String jobName);
	Job[] loadAll();

}
