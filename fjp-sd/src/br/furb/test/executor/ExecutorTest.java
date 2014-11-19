package br.furb.test.executor;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import br.furb.executor.ExecutorImpl;

public class ExecutorTest {
	
	@Test
	public void testSucess() throws IOException, InterruptedException {
		ExecutorImpl executor = new ExecutorImpl(".\\bin", "br.furb.test.executor.ExecutorMainTest");
		Assert.assertEquals(0, executor.execute());		
	}
	
	@Test
	public void testFail() throws IOException, InterruptedException {
		ExecutorImpl executor = new ExecutorImpl(".\\bin", "br.furb.test.executor.INVALID");
		Assert.assertNotEquals(0, executor.execute());		
	}

}
