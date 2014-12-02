package br.furb.corba;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.furb.corba.configuration.history.JobHistory;
import br.furb.corba.configuration.stubs.job_manager;
import br.furb.corba.configuration.stubs.job_managerHelper;

public class JobManagerClient {
	
	public static void main(String[] args) {
		try {
		// Cria e inicializa o ORB
	      ORB orb = ORB.init(args, null);

	      // Obtem referencia para o servico de nomes
	      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	      NamingContextExt namecontextRef = NamingContextExtHelper.narrow(objRef);
	 
	      // Obtem referencia para o servidor
	      String name = ConstantUtils.SERVICE_NAME;
	      job_manager jobManager = job_managerHelper.narrow(namecontextRef.resolve_str(name));
		} catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
			System.out.println("");
		}
	}
	
	public static void addHistory(String jobName, JobHistory history) {
		//TODO Chamar o método via corba
	}

}
