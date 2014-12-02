package br.furb.test.jobManager;

import org.junit.Test;

import br.furb.webservice.client.LoginWeb;
import br.furb.webservice.client.LoginWebService;

public class LoginWebTest {
	
	@Test
	public void testLogin01() {
		LoginWeb login = new LoginWebService().getLoginWebPort();
		boolean bool = login.login("fredyschlag", "123456");
		
		if (!bool) {
			throw new RuntimeException("O WebService executou com erro.");
		}
	}
	
	@Test
	public void testLogin02() {
		br.furb.webservice.client.LoginWeb login = new LoginWebService().getLoginWebPort();
		boolean bool = login.login("fredyschlag", "");
		
		if (bool) {
			throw new RuntimeException("O WebService não executou com erro.");
		}
	}	
}
