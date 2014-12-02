package test;

import java.io.File;
import java.io.IOException;

import main.CreateFile;

import org.junit.Assert;
import org.junit.Test;

public class CreateFileTest {
	
	@Test
	public void test01() throws IOException {
		File file = new File("C:\\temp\\fjp-sd-test-01.txt");		
		file.delete();
		Assert.assertFalse(file.exists());
		CreateFile createFile = new CreateFile(file);
		createFile.execute();
		Assert.assertTrue(file.exists());
		Assert.fail();
	}

}
