package br.furb.corba;

import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.furb.corba.configuration.Job;
import br.furb.corba.configuration.JobType;
import br.furb.corba.configuration.history.HistoryStatus;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.corba.configuration.stubs.History_Status;
import br.furb.corba.configuration.stubs.Job_;
import br.furb.corba.configuration.stubs.Job_History;
import br.furb.corba.configuration.stubs.Job_Holder;
import br.furb.corba.configuration.stubs.Job_Type;
import br.furb.corba.configuration.stubs.arrayJobsHistoryHolder;
import br.furb.corba.configuration.stubs.arrayJobsHolder;
import br.furb.corba.configuration.stubs.job_manager;
import br.furb.corba.configuration.stubs.job_managerHelper;

public class JobManagerClient {
	
	private job_manager jobManager;

	public JobManagerClient(String[] args) {
		try {
			// Cria e inicializa o ORB
			ORB orb = ORB.init(args, null);

			// Obtem referencia para o servico de nomes
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt namecontextRef = NamingContextExtHelper.narrow(objRef);

			// Obtem referencia para o servidor
			String name = ConstantUtils.SERVICE_NAME;
			jobManager = job_managerHelper.narrow(namecontextRef.resolve_str(name));
		} catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
			System.out.println("");
		}
	}

	public void addHistory(String jobName, JobHistory history) {
		History_Status status = History_Status.from_int(history.getStatus().ordinal());
		Job_History historyAdapter = new Job_History(history.getDateInMillis(), history.getLog(), status);
		jobManager.add_history(jobName, historyAdapter);
	}
	
	public JobHistory[] loadHistorys(String jobName) {
		arrayJobsHistoryHolder jobHistorys = new arrayJobsHistoryHolder();
		jobManager.loadHistorys(jobName, jobHistorys);
		Job_History[] historysAdapter = jobHistorys.value;
		
		JobHistory[] historys = new JobHistory[historysAdapter.length];
		for (int i = 0; i < historysAdapter.length; i++) {
			Job_History jobHistoryAdapter = historysAdapter[i];
			HistoryStatus jobHistory = HistoryStatus.values()[jobHistoryAdapter.status.value()];
			historys[i] = new JobHistory(jobHistoryAdapter.log, jobHistory, jobHistoryAdapter.dateInMillis);
		}
		
		return historys;
	}
	
	public Job[] loadAll() {
		arrayJobsHolder jobsHolder = new arrayJobsHolder();
		jobManager.load_all(jobsHolder);
		Job_[] jobsAdapter = jobsHolder.value;
		
		Job[] jobs = new Job[jobsAdapter.length];
		for (int i = 0; i < jobsAdapter.length; i++) {
			Job_ jobAdapter = jobsAdapter[i];
			JobType jobType = JobType.values()[jobAdapter.job_type.value()];
			jobs[i] = new Job(jobAdapter.job_nome, jobType, jobAdapter.repository_Path);
		}
		
		return jobs;
	}
	
	public Job load(String jobName) {
		Job_Holder jobHolder = new Job_Holder();
		jobManager.load(jobName, jobHolder);
		
		Job_ jobAdapter = jobHolder.value;
		JobType jobType = JobType.values()[jobAdapter.job_type.value()];
		Job job = new Job(jobAdapter.job_nome, jobType, jobAdapter.repository_Path);
		
		return job;
	}
	
	public void delete(String jobName) {
		jobManager.delete(jobName);
	}
	
	public boolean exist(String jobName) {
		BooleanHolder existHolder = new BooleanHolder();
		jobManager.exist(jobName, existHolder);
		
		return existHolder.value;
	}
	
	public void save(Job job) {
		Job_Type jobTypeAdapter = Job_Type.from_int(job.getType().ordinal());
		Job_ jobAdapter = new Job_(job.getName(), jobTypeAdapter, job.getRepositoryPath());
		jobManager.save(jobAdapter);
	}

}
