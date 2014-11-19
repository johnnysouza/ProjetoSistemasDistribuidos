package br.furb.git;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class GitClient {
	
	public void login() throws IOException {
		GitHub github = GitHub.connect();
		github.connectAnonymously();
		GHRepository repository = github.getRepository("");
	}

}
