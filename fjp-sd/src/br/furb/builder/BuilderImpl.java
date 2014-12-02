package br.furb.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import br.furb.config.ConfigHelper;
import br.furb.rmi.Builder;

public class BuilderImpl implements Builder {

	private boolean execute(String operation, String projectDir) {
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

			return (exitCode == 0) && !failure;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean compile(String projectDir) {
		return execute("compile", projectDir);
	}

	@Override
	public boolean test(String projectDir) {
		return execute("test", projectDir);
	}

	public static void main(String[] args) {
		Builder builder = new BuilderImpl();
		System.out
				.println("##############################################################");
		System.out
				.println(builder
						.compile("C:\\Users\\Fredy\\git\\ProjetoSistemasDistribuidos\\fjp-sd-test"));
		System.out
				.println("##############################################################");
		System.out
				.println(builder
						.test("C:\\Users\\Fredy\\git\\ProjetoSistemasDistribuidos\\fjp-sd-test"));
		System.out
				.println("##############################################################");
	}

}
