package br.furb.corba.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class JobManager implements JobManagerDao {

	private File jobsPath;

	public JobManager() {
		jobsPath = new File(System.getProperty("user.dir").concat(File.pathSeparator).concat("\\fjp_sd"));
		jobsPath.mkdir();
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
		FileOutputStream fileWriter = null;
		ObjectOutputStream objectWriter = null;
		try {
			fileWriter = new FileOutputStream(jobFile);
			objectWriter = new ObjectOutputStream(fileWriter);
			objectWriter.writeObject(job);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
				if (objectWriter != null) {
					objectWriter.close();
				}
			} catch (IOException ex) {
				System.out.println("Erro na liberação dos leitores do arquivo");
			}
		}
		
		// Cria a pasta do hisrótico do job se não existe ainda
		File jobHistoryPath = new File(jobPath.getAbsolutePath().concat(File.separator).concat("history"));
		if (!jobHistoryPath.exists()) {
			jobHistoryPath.mkdir();
		}
	}

	@Override
	public boolean exist(String jobName) {
		// Auxiliar com o caminho até a pasta do job
		String jobPath = jobsPath.getAbsolutePath().concat(File.separator).concat(jobName);

		// Arquivo do job
		File jobFile = new File(jobPath.concat(File.separator).concat(jobName).concat(File.separator).concat(".dat"));

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
	public Job load(File path) {
		Job job = null;

		if (path.exists()) {
			FileInputStream fileReader = null;
			ObjectInputStream objectReader = null;
			try {
				fileReader = new FileInputStream(path);
				objectReader = new ObjectInputStream(fileReader);
				job = (Job) objectReader.readObject();
			} catch (FileNotFoundException e) {
				System.out.println("Arquivo não encontrado");
			} catch (IOException e) {
				System.out.println("Erro na leitura do arquivo");
			} catch (ClassNotFoundException e) {
				System.out.println("Erro no carregamento do job");
			} finally {
				try {
					if (fileReader != null) {
						fileReader.close();
					}
					if (objectReader != null) {
						objectReader.close();
					}
				} catch (IOException ex) {
					System.out.println("Erro na liberação dos leitores do arquivo");
				}
			}
		} else {
			System.out.println("Diretório informado não existe");
		}

		return job;
	}

	@Override
	public List<Job> loadAll() {
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
						jobs.add(load(file));
					}
				}

			}
		}

		return jobs;
	}

}
