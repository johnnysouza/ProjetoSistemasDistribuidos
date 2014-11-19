package br.furb.executor;

import java.io.IOException;

public class ExecutorImpl {
	
	private String classpath;
	private String mainClass;
	
	public ExecutorImpl(String classpath, String mainClass) {
		this.classpath = classpath;
		this.mainClass = mainClass;
	}
	
	public int execute() throws IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		builder.append("java ");
		
		if ((classpath != null) || (!classpath.isEmpty())) {
			builder.append("-cp ");
			builder.append(classpath);
			builder.append(" ");
		}
		
		builder.append(mainClass);
		
		Process process = Runtime.getRuntime().exec(builder.toString());
		return process.waitFor();
	}

}
