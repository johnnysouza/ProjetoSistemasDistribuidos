package br.furb.rmi;

import java.rmi.Naming;

public class BuilderClient {
	
	public static void main(String[] args) {
		try {
			Builder obj = (Builder) Naming.lookup("//localhost/builder");
			obj.compile("fjp-sd-test", "C:\\Temp\\testfjp\\fjp-sd-test");
			obj.test("fjp-sd-test", "C:\\Temp\\testfjp\\fjp-sd-test");
			obj.packageJar("fjp-sd-test", "C:\\Temp\\testfjp\\fjp-sd-test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
