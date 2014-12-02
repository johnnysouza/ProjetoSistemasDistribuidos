package br.furb.gui;

import java.rmi.RemoteException;
import java.util.Scanner;

import br.furb.corba.configuration.Job;
import br.furb.corba.configuration.JobType;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.facade.MiddlewareFacade;

public class ConsoleApplication {

	private static Scanner in;
	private static MiddlewareFacade facade;

	public static void main(String[] args) {
		facade = MiddlewareFacade.getInstance();
		in = new Scanner(System.in);
		System.out.println("Sistemas distribuídos");
		System.out.println("Usuário: ");
		String userName = in.nextLine();
		System.out.println("Senha: ");
		String password = in.nextLine();

		if (!facade.login(userName, password)) {
			throw new RuntimeException("Usuário inválido.");
		}

		int opcao = 6;
		do {
			System.out.println("Escolha a operação que deseja realizar:");
			System.out.println("1 - Cadastrar job");
			System.out.println("2 - Remover job");
			System.out.println("3 - Listar jobs");
			System.out.println("4 - Executar job");
			System.out.println("5 - Listar histórico");
			System.out.println("6 - Sair");
			opcao = Integer.parseInt(in.nextLine());

			switch (opcao) {
			case 1:
				cadastrarJob();
				break;
			case 2:
				removerJob();
				break;
			case 3:
				listarJobs();
				break;
			case 4:
				executarJob();
				break;
			case 5:
				listarHistorico();
				break;
			}
		} while (opcao != 6);
	}

	private static void listarHistorico() {
		System.out.println("Informe a job:");
		String jobName = in.nextLine();
		JobHistory[] jobs = facade.loadHistorys(jobName);
		
		for (JobHistory history : jobs) {
			System.out.println(history.toString());
		}
	}

	private static boolean executarJob(Job job) throws RemoteException {
		switch (job.getType()) {
		case CLONE:
			return facade
					.cloneRepository(job.getName(), job.getRepositoryPath(),
							"C:\\temp\\jobs\\" + job.getName());
		case COMPILATION:
			return facade.compile(job.getName(), job.getRepositoryPath());
		case TEST:
			return facade.test(job.getName(), job.getRepositoryPath());
		case BUILD:
			return facade.packageJar(job.getName(), job.getRepositoryPath());
		}

		return false;
	}

	private static void executarJob() {
		System.out.println("Informe a job para ser executada:");
		String jobName = in.nextLine();
		try {
			Job job = facade.load(jobName);
			if (executarJob(job)) {
				System.out.println("Job executada com sucesso.");
			} else {
				System.out.println("Job executada com falha.");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void listarJobs() {
		Job[] jobs = facade.loadAll();

		for (Job job : jobs) {
			System.out.println(job.toString());
		}
	}

	private static void removerJob() {
		System.out.println("Informe a job para ser executada:");
		String jobName = in.nextLine();
		facade.delete(jobName);
		System.out.println("Job removida");
	}

	private static Job cadastrarJob() {
		System.out.println("Cadastro de job");
		System.out.println("Nome: ");
		String name = in.nextLine();
		System.out
				.println("Type (0 - Clone / 1 - Compilation / 2 - Test / 3 - Build): ");
		int typeInt = Integer.parseInt(in.nextLine());
		JobType type;

		switch (typeInt) {
		case 0:
			type = JobType.CLONE;
			break;
		case 1:
			type = JobType.COMPILATION;
			break;
		case 2:
			type = JobType.TEST;
			break;
		case 3:
			type = JobType.BUILD;
			break;
		default:
			type = null;
		}

		System.out.println("RepositoryPath: ");
		String repositoryPath = in.nextLine();
		Job job = new Job(name, type, repositoryPath);
		facade.save(job);
		return job;
	}

}
