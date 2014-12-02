package main;

import java.io.File;
import java.io.IOException;

public class CreateFile {
	
	private File file;
	
	public CreateFile(File file) {
		this.file = file;
	}
	
	public void execute() throws IOException {
		file.createNewFile();
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		CreateFile task = new CreateFile(file);
		task.execute();
	}	

}
