package com.osgi.ejb.integration.EJB.SLSB;

import java.io.File;
import java.io.IOException;

import javax.ejb.Remote;

@Remote
public interface GD {

	public String testHello();

	public String getVerificationUrl();
	
	public void login(String code) throws IOException;
	
	public void uplaodFile(File file,String title, String description, String mimeType) throws IOException;
	
}
