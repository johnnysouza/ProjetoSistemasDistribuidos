package br.furb.test.jobManager;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import br.furb.corba.configuration.Job;
import br.furb.corba.configuration.JobManager;
import br.furb.corba.configuration.JobType;

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

}
