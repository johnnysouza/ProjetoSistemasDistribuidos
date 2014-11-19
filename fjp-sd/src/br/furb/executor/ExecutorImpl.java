package br.furb.executor;

import java.io.IOException;

public class ExecutorImpl {
	
	private String classpath;
	private String mainClass;
	private String[] params;
	
	public ExecutorImpl(String classpath, String mainClass, String[] params) {
		this.classpath = classpath;
		this.mainClass = mainClass;
		this.params = params;
		
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
		
		if (params != null) {						
			for (String param : params) {
				builder.append(" ");
				builder.append(param);
			}
		}
		
		Process process = Runtime.getRuntime().exec(builder.toString());
		return process.waitFor();
	}

}
