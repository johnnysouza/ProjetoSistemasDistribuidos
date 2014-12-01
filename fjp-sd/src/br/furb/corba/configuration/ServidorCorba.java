package br.furb.corba.configuration;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import br.furb.corba.ConstantUtils;
import br.furb.corba.configuration.stubs.job_manager;
import br.furb.corba.configuration.stubs.job_managerHelper;

public class ServidorCorba {
	
	public static void main(String[] args) {
		try{
		      // Cria e inicializa o ORB
		      ORB orb = ORB.init(args, null);

		      // Cria a implementa��o e registra no ORB
		      job_managerImpl job_manager = new job_managerImpl();

		      // Ativa o POA
		      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		      rootpoa.the_POAManager().activate();

		      // Pega a refer�ncia do servidor
		      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(job_manager);
		      job_manager href = job_managerHelper.narrow(ref);
			  
		      // Obt�m uma refer�ncia para o servidor de nomes
		      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		      NamingContextExt namecontextRef = NamingContextExtHelper.narrow(objRef);

		      // Registra o servidor no servico de nomes
		      String name = ConstantUtils.SERVICE_NAME;
		      NameComponent path[] = namecontextRef.to_name(name);
		      namecontextRef.rebind(path, href);

		      System.out.println("Servidor aguardando requisicoes ....");

		      // Aguarda chamadas dos clientes
		      orb.run();
		    } catch (Exception e) {
		        System.err.println("ERRO: " + e);
		        e.printStackTrace(System.out);
		    }
		    System.out.println("Encerrando o Servidor.");
	}

}
