package br.furb.webservice;

import java.io.FileInputStream;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class LoginWeb {
	
	@WebMethod
	public boolean login(String userName, String password) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("login.properties"));
			String passwordProp = properties.getProperty(userName);
			return (passwordProp != null) && (password.equals(passwordProp));
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
	}

}
