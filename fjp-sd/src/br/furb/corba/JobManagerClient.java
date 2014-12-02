package br.furb.corba;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.furb.corba.configuration.Job;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.corba.configuration.stubs.History_Status;
import br.furb.corba.configuration.stubs.Job_History;
import br.furb.corba.configuration.stubs.arrayJobsHistoryHolder;
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
		arrayJobsHistoryHolder historys = new arrayJobsHistoryHolder();
		jobManager.loadHistorys(jobName, historys);
		Job_History[] historysAdapter = historys.value;
		//TODO
		return null;
	}
	
	public Job[] loadAll() {
		//TODO
		return null;
	}

}
