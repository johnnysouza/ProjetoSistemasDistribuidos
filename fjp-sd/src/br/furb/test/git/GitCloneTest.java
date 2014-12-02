package br.furb.test.git;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import br.furb.git.GitClone;
import br.furb.webservice.client.GitCloneWeb;
import br.furb.webservice.client.GitCloneWebService;

public class GitCloneTest {
	
	@Test
	public void test01() throws IOException {
		new File("C:\\temp\\testRepository\\").delete();
		GitClone git = new GitClone("https://github.com/johnnysouza/ProjetoSistemasDistribuidos", "C:\\temp\\testRepository");
		git.execute();
	}
	
	@Test
	public void testWebService01() {
		new File("C:\\temp\\testRepository\\").delete();
		GitCloneWeb clone = new GitCloneWebService().getGitCloneWebPort();
		boolean bool = clone.cloneRepository("https://github.com/johnnysouza/ProjetoSistemasDistribuidos", "C:\\temp\\testRepository");
		
		if (!bool) {
			throw new RuntimeException("O WebService executou com erro.");
		}
	}
	
	@Test
	public void testWebService02() {
		GitCloneWeb clone = new GitCloneWebService().getGitCloneWebPort();
		boolean bool = clone.cloneRepository("https://github.com/nuncavaiexistir", "C:\\temp\\testRepository");
		
		if (bool) {
			throw new RuntimeException("Deveria ter ocorrido erro na execução do Web Service.");
		}
	}	

}
