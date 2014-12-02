package br.furb.corba.configuration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.furb.corba.ConstantUtils;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.corba.configuration.history.JobHistoryDAO;

public class JobManager implements JobConfigurationDao, JobHistoryDAO {

	private static final short NUM_JOB_TO_KEEP = 10;
	private static JobManager instance;
	public File jobsPath;

	private JobManager() {
		jobsPath = new File(System.getProperty("user.dir").concat(File.separator).concat("fjp_sd"));
		if (!jobsPath.exists()) {
			jobsPath.mkdir();
		}
	}

	public static JobManager getInstance() {
		if (instance == null) {
			instance = new JobManager();
		}
		return instance;
	}

	public File getJobsPath() {
		return jobsPath;
	}

	@Override
	public void save(Job job) {
		// Auxiliar com \"nomeJob"
		String jobSuffix = File.separator.concat(job.getName());

		// Pasta onde do job
		File jobPath = new File(jobsPath.getAbsolutePath().concat(jobSuffix));

		// Se não existe a pasta do job ainda cria ela
		if (!jobPath.exists()) {
			jobPath.mkdir();
		}

		// Arquivo do job
		File jobFile = new File(jobPath.getAbsolutePath().concat(jobSuffix).concat(".dat"));

		// Se já existe deleta
		if (jobFile.exists() && jobFile.isFile()) {
			jobFile.delete();
		}

		// Serializa o job num arquivo .dat
		SerializationUtils.serializeObject(job, jobFile);

		// Cria a pasta do hisrótico do job se não existe ainda
		File jobHistoryPath = new File(jobPath.getAbsolutePath().concat(File.separator).concat(ConstantUtils.JOB_HISTORY));
		if (!jobHistoryPath.exists()) {
			jobHistoryPath.mkdir();
		}
	}

	@Override
	public boolean exist(String jobName) {
		// Carrega o arquivo do job
		File jobFile = convertJobNameToJobFile(jobName);

		// Verifica existência
		if (jobFile.exists()) {
			return true;
		}
		return false;
	}

	@Override
	public void delete(String jobName) {
		// Pega o arquivo do job a ser deletado na pasta do projeto de integração continua
		File jobPath = new File(jobsPath.getAbsolutePath().concat(File.separator).concat(jobName));

		// Deleta a pasta do job e todos os seus arquivos nela contidos
		if (jobPath.exists()) {
			if (jobsPath.isDirectory()) {
				try {
					FileUtils.deleteDirectory(jobPath);
				} catch (IOException e) {
					System.out.println("Erro ao deletar arquivo");
				}
			} else {
				System.out.println("Job informado não existe");
			}
		}
	}

	@Override
	public Job load(String jobName) {
		// Carrega o arquivo do job
		File jobFile = convertJobNameToJobFile(jobName);
		Job job = null;

		if (jobFile.exists()) {
			Object objectSerialized = SerializationUtils.deserealizeFile(jobFile);
			
			if (objectSerialized != null) {
				job = (Job) objectSerialized;
			}
		} else {
			System.out.println("Diretório informado não existe");
		}

		return job;
	}

	@Override
	public Job[] loadAll() {
		// Cria a lista dos jobs
		List<Job> jobs = new ArrayList<Job>();

		// Busca na pasta do projeto de integração continua e lista os arquivos
		File[] jobsDiretory = jobsPath.listFiles();

		// Busca pelos arquivos, os que são as pastas dos projeto
		for (File path : jobsDiretory) {
			if (path.isDirectory()) {

				// Lista os arquivo dentro da pasta do projeto
				File[] jobFiles = path.listFiles();

				// Procura o arquivo com as informações do projeto
				for (File file : jobFiles) {
					if (file.getName().endsWith(".dat")) {
						// Adiciona o job na lista
						jobs.add(load(file.getName().replace(".dat", "")));
					}
				}

			}
		}

		Job[] ret = new Job[jobs.size()];
		for (int i = 0, size = jobs.size(); i < size; i++) {
			ret[i] = jobs.get(i);
		}

		return ret;
	}

	private File convertJobNameToJobFile(String jobName) {
		// Auxiliar com o caminho até a pasta do job
		String jobPath = jobsPath.getAbsolutePath().concat(File.separator).concat(jobName);

		// Arquivo do job
		return new File(jobPath.concat(File.separator).concat(jobName).concat(".dat"));
	}

	@Override
	public void addHistory(String jobName, JobHistory history) {
		// Localiza a pasta de historicos do job
		String jobPath = jobsPath.getAbsolutePath().concat(File.separator).concat(jobName);
		File historyPath = new File(jobPath.concat(File.separator).concat(ConstantUtils.JOB_HISTORY));

		if (historyPath.isDirectory()) {
			File[] historys = historyPath.listFiles();

			// Verifica se já atingiu o limete configurado (fixo por enquanto)
			if (historys.length == NUM_JOB_TO_KEEP) {
				// Deleta o job mais antigo
				findOldestHistory(historys).delete();
			}

			// Busca data atual para definir nome do historico
			Date date = new Date(history.getDateInMillis());
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
			File historyFile = new File(format.format(date));

			// Serializa o historico num arquivo .dat
			SerializationUtils.serializeObject(history, historyFile);
		}
	}

	@Override
	public JobHistory[] loadHistorys(String jobName) {
		JobHistory[] loadHistorys = null;

		String jobPath = jobsPath.getAbsolutePath().concat(File.separator).concat(jobName);
		File historyPath = new File(jobPath.concat(File.separator).concat(ConstantUtils.JOB_HISTORY));

		if (historyPath.isDirectory()) {
			File[] historys = historyPath.listFiles();
			loadHistorys = new JobHistory[historys.length];

			//Faz o parser do array de arquivos em array de historico de job
			for (int i = 0; i < historys.length; i++) {
				Object objectSerialized = SerializationUtils.deserealizeFile(historys[i]);
				
				if (objectSerialized != null) {
					loadHistorys[i] = (JobHistory) objectSerialized;
				}
			}
		}

		return loadHistorys;
	}

	private File findOldestHistory(File[] historys) {
		// TODO implementar
		return null;
	}

}
