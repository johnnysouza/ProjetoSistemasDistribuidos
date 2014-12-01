package br.furb.corba.configuration;

import org.omg.CORBA.BooleanHolder;

import br.furb.corba.configuration.stubs.Job;
import br.furb.corba.configuration.stubs.JobHolder;
import br.furb.corba.configuration.stubs.arrayJobsHolder;
import br.furb.corba.configuration.stubs.job_managerPOA;

public class job_managerImpl extends job_managerPOA {
	
	private static final JobManager jobManager = JobManager.getInstance();

	@Override
	public void save(Job job) {
		br.furb.corba.configuration.JobType jobTypeAdapter = br.furb.corba.configuration.JobType.values()[job.job_type.value()];
		br.furb.corba.configuration.Job jobAdapter = new br.furb.corba.configuration.Job(job.job_nome, jobTypeAdapter, job.repository_Path);
		jobManager.save(jobAdapter);
	}

	@Override
	public boolean exist(String job_nome, BooleanHolder ret) {
		ret.value = jobManager.exist(job_nome);
		return true;
	}

	@Override
	public void delete(String job_nome) {
		jobManager.delete(job_nome);
	}

	@Override
	public boolean load(String path, JobHolder ret) {
		br.furb.corba.configuration.Job jobAdapter = jobManager.load(path);
		br.furb.corba.configuration.stubs.JobType jobType = br.furb.corba.configuration.stubs.JobType.from_int(jobAdapter.getType().ordinal());
		ret.value = new Job(jobAdapter.getName(), jobType, jobAdapter.getRepositoryPath());
		return true;
	}

	@Override
	public boolean load_all(arrayJobsHolder jobsHolder) {
		br.furb.corba.configuration.Job[] jobsAdapter = jobManager.loadAll();
		Job[] jobs = new Job[jobsAdapter.length];
		for (int i = 0; i < jobsAdapter.length; i++) {
			br.furb.corba.configuration.Job jobAdapter = jobsAdapter[i];
			br.furb.corba.configuration.stubs.JobType jobType = br.furb.corba.configuration.stubs.JobType.from_int(jobAdapter.getType().ordinal());
			jobs[i] = new Job(jobAdapter.getName(), jobType, jobAdapter.getRepositoryPath());
		}
		jobsHolder.value = jobs.clone();
		return true;
	}

}
