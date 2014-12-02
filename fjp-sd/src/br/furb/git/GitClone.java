package br.furb.git;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitClone {
	
	private String targetDir;
	private String repository;
	
	public GitClone(String repository, String targetDir) {
		this.repository = repository;
		this.targetDir = targetDir;
	}
	
	public void execute() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(GitConfig.getInstance().getGitCmd());
		sb.append("\" clone \"");
		sb.append(repository);
		sb.append("\" ");
		sb.append(targetDir);
		sb.append("\"");
		Process process = Runtime.getRuntime().exec(sb.toString());
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		System.out.println("Saída do padrão do processo:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}

		System.out.println("Saída de erro:\n");
		while ((s = stdError.readLine()) != null) {
		    System.out.println(s);
		}
		
		int exitCode = 1;
		try {
			exitCode = process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (exitCode != 0) {
			throw new RuntimeException(String.format("Não foi possível clonar o repositório %s para o diretório %s", repository, targetDir));
		}
	}
}
