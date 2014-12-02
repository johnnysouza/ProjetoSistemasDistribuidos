package br.furb.corba.configuration;

import org.omg.CORBA.BooleanHolder;

import br.furb.corba.configuration.history.HistoryStatus;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.corba.configuration.stubs.History_Status;
import br.furb.corba.configuration.stubs.Job_;
import br.furb.corba.configuration.stubs.Job_History;
import br.furb.corba.configuration.stubs.Job_Holder;
import br.furb.corba.configuration.stubs.Job_Type;
import br.furb.corba.configuration.stubs.arrayJobsHistoryHolder;
import br.furb.corba.configuration.stubs.arrayJobsHolder;
import br.furb.corba.configuration.stubs.job_managerPOA;

public class job_managerImpl extends job_managerPOA {
	
	private static final JobManager jobManager = JobManager.getInstance();

	@Override
	public void save(Job_ job) {
		JobType jobTypeAdapter = JobType.values()[job.job_type.value()];
		Job jobAdapter = new Job(job.job_nome, jobTypeAdapter, job.repository_Path);
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
	public boolean load(String jobName, Job_Holder ret) {
		Job jobAdapter = jobManager.load(jobName);
		Job_Type jobType = Job_Type.from_int(jobAdapter.getType().ordinal());
		ret.value = new Job_(jobAdapter.getName(), jobType, jobAdapter.getRepositoryPath());
		return true;
	}

	@Override
	public boolean load_all(arrayJobsHolder jobsHolder) {
		Job[] jobsAdapter = jobManager.loadAll();
		Job_[] jobs = new Job_[jobsAdapter.length];
		for (int i = 0; i < jobsAdapter.length; i++) {
			Job jobAdapter = jobsAdapter[i];
			Job_Type jobType = Job_Type.from_int(jobAdapter.getType().ordinal());
			jobs[i] = new Job_(jobAdapter.getName(), jobType, jobAdapter.getRepositoryPath());
		}
		jobsHolder.value = jobs.clone();
		return true;
	}

	@Override
	public void add_history(String job_nome, Job_History history) {
		HistoryStatus status = HistoryStatus.values()[history.status.value()];
		JobHistory jobhistoryAdapter = new JobHistory(history.log, status, history.dateInMillis);
		jobManager.addHistory(job_nome, jobhistoryAdapter);
	}

	@Override
	public boolean loadHistorys(String job_nome, arrayJobsHistoryHolder historysHolder) {
		JobHistory[] jobsHistoryAdapter = jobManager.loadHistorys(job_nome);
		Job_History[] historys = new Job_History[jobsHistoryAdapter.length];
		
		for (int i = 0; i < jobsHistoryAdapter.length; i++) {
			JobHistory jobHistoryAdapter = jobsHistoryAdapter[i];
			History_Status jobHistory = History_Status.from_int(jobHistoryAdapter.getStatus().ordinal());
			historys[i] = new Job_History(jobHistoryAdapter.getDateInMillis(), jobHistoryAdapter.getLog(), jobHistory);
		}
		
		historysHolder.value = historys.clone();
		return true;
	}

}
