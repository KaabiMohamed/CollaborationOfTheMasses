package com.osgi.ejb.integration.service;

import java.io.IOException;

public interface GoogleDriveService {

	public String sayHello();
	public String addFile(java.io.File file,String title,String description,String mimeType) throws IOException;
	public AuthorisationHelper getAuthorisationHelper();
	public void login(String code) throws IOException;
}
