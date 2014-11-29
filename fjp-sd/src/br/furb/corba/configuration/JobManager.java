package br.furb.corba.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
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
//		File file = new File(jobsPath.getAbsolutePath().concat(
//				File.pathSeparator));
		//TODO
	}

	@Override
	public void delete(String job) {
		// TODO Auto-generated method stub
	}

	@Override
	public Job load(File path) {
		Job job = null;
		if (path.exists()) {
			FileInputStream fis = null;
			ObjectInput jobFile = null;
			try {
				fis = new FileInputStream(path);
				jobFile = new ObjectInputStream(fis);
				job = (Job) jobFile.readObject();
			} catch (FileNotFoundException e) {
				System.out.println("Arquivo não encontrado");
			} catch (IOException e) {
				System.out.println("Erro na leitura do arquivo");
			} catch (ClassNotFoundException e) {
				System.out.println("Erro no carregamento do job");
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (jobFile != null) {
						jobFile.close();
					}
				} catch (IOException ex) {
					System.out.println("Erro na liberação do arquivo");
				}
			}
		}
		return job;
	}

	@Override
	public List<Job> loadAll(String path) {
		File[] files = jobsPath.listFiles();
		for (File file : files) {
			if (file.getName().endsWith(".dat")) {

			}
		}
		// TODO Auto-generated method stub
		return null;
	}

}
