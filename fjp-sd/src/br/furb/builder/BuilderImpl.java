package br.furb.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import br.furb.config.ConfigHelper;
import br.furb.corba.JobManagerClient;
import br.furb.corba.configuration.history.HistoryStatus;
import br.furb.corba.configuration.history.JobHistory;
import br.furb.rmi.Builder;

public class BuilderImpl implements Builder {

	private boolean execute(String operation, String projectDir, String jobName) {
		boolean status = false;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(ConfigHelper.getInstance().getMvnCmd());
			sb.append(" ");
			sb.append(operation);
			Process process = Runtime.getRuntime().exec(sb.toString(), null,
					new File(projectDir));
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			boolean failure = false;

			System.out.println("Saída do padrão do processo:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
				failure = failure || s.contains("BUILD FAILURE");
			}

			System.out.println("Saída de erro:\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
				failure = failure || s.contains("BUILD FAILURE");
			}

			int exitCode = 1;
			try {
				exitCode = process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			status = (exitCode == 0) && !failure;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JobManagerClient.addHistory(jobName, new JobHistory(operation, status ? HistoryStatus.SUCCESS : HistoryStatus.FAIL, System.currentTimeMillis()));
		return status;
	}	

	@Override
	public boolean compile(String jobName, String projectDir) {
		return execute("compile", projectDir, jobName);		
	}

	@Override
	public boolean test(String jobName, String projectDir) {
		return execute("test", projectDir, jobName);
	}

	@Override
	public boolean packageJar(String jobName, String projectDir) {
		return execute("package", projectDir, jobName);
	}

	public static void main(String[] args) {
		Builder builder = new BuilderImpl();
		System.out
				.println("##############################################################");
		System.out.println(builder.compile("fjp-sd-test", "C:\\Temp\\testfjp\\fjp-sd-test"));
		System.out
				.println("##############################################################");
		System.out.println(builder.test("fjp-sd-test", "C:\\Temp\\testfjp\\fjp-sd-test"));
		System.out
				.println("##############################################################");
		System.out.println(builder.packageJar("fjp-sd-test", "C:\\Temp\\testfjp\\fjp-sd-test"));
		System.out
				.println("##############################################################");
	}

}
