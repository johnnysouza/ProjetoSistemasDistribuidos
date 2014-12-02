package br.furb.webservice;

import java.io.File;
import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.furb.corba.JobManagerClient;
import br.furb.corba.configuration.history.HistoryStatus;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.facade.MiddlewareFacade;
import br.furb.git.GitClone;

@WebService
public class GitCloneWeb {

	@WebMethod
	public boolean cloneRepository(String jobName, String repository,
			String targetDir) {
		boolean status = false;
		GitClone git = new GitClone(repository, targetDir);
		try {
			File file = new File(targetDir);
			if (file.exists()) {
				delete(file);
			}

			git.execute();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		MiddlewareFacade.getInstance().addHistory(
				jobName,
				new JobHistory("clone", status ? HistoryStatus.SUCCESS
						: HistoryStatus.FAIL, System.currentTimeMillis()));
		return status;
	}

	public static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : "
						+ file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : "
							+ file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

}
