package br.furb.test.jobManager;

import static org.junit.Assert.*;

import java.io.File;

import javax.print.attribute.standard.JobState;

import org.junit.Assert;
import org.junit.Test;

import br.furb.corba.configuration.Job;
import br.furb.corba.configuration.JobManager;
import br.furb.corba.configuration.JobType;
import br.furb.corba.configuration.history.HistoryStatus;
import br.furb.corba.configuration.history.JobHistory;

public class JobManagerTest {

	/**
	 * Testa configuração do job
	 */
	@Test
	public void teste0001() {
		JobManager jobManager = JobManager.getInstance();
		Job job = new Job("teste", JobType.COMPILATION, "123");
		jobManager.save(job);

		String jobPath = jobManager.getJobsPath().getAbsolutePath().concat(File.separator).concat("teste");

		// Verifica se criou a pasta do job
		assertTrue(new File(jobPath).exists());

		// verifica se criou o arquivo do job
		assertTrue(new File(jobPath.concat(File.separator).concat("teste.dat")).exists());

		// Verifica se criou a pasta de historico do job
		assertTrue(new File(jobPath.concat(File.separator).concat("history")).exists());

		// Agora verifica se existe pelo método exist
		assertTrue(jobManager.exist("teste"));

		// Verifica carregamento de todos os job
		assertEquals("Job [name=teste, type=COMPILATION, repositoryPath=123]", jobManager.loadAll()[0].toString());

		job = new Job("teste", JobType.COMPILATION, "1234");
		jobManager.save(job);

		// Verifica carregamento individual do job
		assertEquals("Job [name=teste, type=COMPILATION, repositoryPath=1234]", jobManager.load("teste").toString());

		// deleta e verifica
		jobManager.delete("teste");
		// Verifica se deletou a pasta do job
		assertFalse(new File(jobPath).exists());

		// verifica se deletou o arquivo do job
		assertFalse(new File(jobPath.concat(File.separator).concat("teste.dat")).exists());

		// Verifica se deletou a pasta de historico do job
		assertFalse(new File(jobPath.concat(File.separator).concat("history")).exists());

		// Agora verifica se deletou pelo método criado exist
		assertFalse(jobManager.exist("teste"));
	}
	
	/**
	 * Testa criação de historico do job
	 */
	@Test
	public void teste0002() {
		JobManager jobManager = JobManager.getInstance();
		Job job = new Job("teste0002", JobType.COMPILATION, "123");
		jobManager.save(job);
		
		jobManager.addHistory("teste0002", new JobHistory("teste0002", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		
		String jobPath = jobManager.getJobsPath().getAbsolutePath().concat(File.separator).concat("teste0002");
		File historyJobPath = new File(jobPath.concat(File.separator).concat("history"));
		assertTrue(historyJobPath.list().length == 1);
		
		jobManager.delete("teste0002");
	}
	
	/**
	 * Testa carregamento dos historicos do job
	 * @throws InterruptedException 
	 */
	@Test
	public void teste0003() throws InterruptedException {
		JobManager jobManager = JobManager.getInstance();
		Job job = new Job("teste0003", JobType.COMPILATION, "123");
		jobManager.save(job);
		
		jobManager.addHistory("teste0003", new JobHistory("1", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0003", new JobHistory("2", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		
		String jobPath = jobManager.getJobsPath().getAbsolutePath().concat(File.separator).concat("teste0003");
		File historyJobPath = new File(jobPath.concat(File.separator).concat("history"));
		assertTrue(historyJobPath.list().length == 2);
		
		JobHistory[] jobHistorys = jobManager.loadHistorys("teste0003");
		assertEquals("1", jobHistorys[0].getLog());
		assertEquals("2", jobHistorys[1].getLog());
		
		jobManager.delete("teste0003");
	}
	
	/**
	 * Teste da rotatividade dos historicos do job
	 * @throws InterruptedException 
	 */
	@Test
	public void teste0004() throws InterruptedException {
		JobManager jobManager = JobManager.getInstance();
		Job job = new Job("teste0004", JobType.COMPILATION, "123");
		jobManager.save(job);
		
		jobManager.addHistory("teste0004", new JobHistory("1", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("2", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("3", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("4", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("5", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("6", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("7", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("8", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("9", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("10", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		jobManager.addHistory("teste0004", new JobHistory("11", HistoryStatus.SUCCESS, System.currentTimeMillis()));
		Thread.sleep(1000);
		
		String jobPath = jobManager.getJobsPath().getAbsolutePath().concat(File.separator).concat("teste0004");
		File historyJobPath = new File(jobPath.concat(File.separator).concat("history"));
		assertTrue(historyJobPath.list().length == 10);
		
		JobHistory[] jobHistorys = jobManager.loadHistorys("teste0004");
		assertEquals("1", jobHistorys[0].getLog());
		
		jobManager.delete("teste0004");
	}

}
