package br.furb.webservice;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.furb.git.GitClone;

@WebService
public class GitCloneWeb {
	
	@WebMethod
	public boolean cloneRepository(String repository, String targetDir) {
		GitClone git = new GitClone(repository, targetDir);
		try {
			git.execute();
		} catch (Exception e) {		
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
