package br.furb.corba.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class JobManager implements JobManagerDao {

	private File jobsPath;

	public JobManager() {
		jobsPath = new File(System.getProperty("user.dir")
				.concat(File.pathSeparator).concat("\\fjp_sd"));
		jobsPath.mkdir();
	}

	@Override
	public void save(String jobName) {
		// File file = new File(jobsPath.getAbsolutePath().concat(
		// File.pathSeparator));
		// TODO
	}

	@Override
	public void delete(String job) {
		// TODO Auto-generated method stub
	}

	@Override
	public Job load(File path) {
		Job job = null;
		if (path.exists()) {
			FileInputStream fileReader = null;
			ObjectInput objectReader = null;
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
					System.out
							.println("Erro na liberação dos leitores do arquivo");
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

		// Busca na pasta do projeto de integração e lista os arquivos
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
