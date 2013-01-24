package com.osgi.ejb.integration.service;

import java.io.IOException;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;



public class GoogleDriveServiceImpl implements GoogleDriveService{

	private AuthorisationHelper authorisationHelper=AuthorisationHelper.getINSTANCE();
	
	Drive drive;
	
	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return "--------- Hello -----------";
	}
	
	
	public String addFile(java.io.File file,String title,String description,String mimeType) throws IOException{
	
	 File body = new File();	
		body.setTitle(title);
		body.setDescription(description);
		body.setMimeType(mimeType);
		FileContent mediaContent = new FileContent("text/plain", file);
		File gFile = drive.files().insert(body, mediaContent).execute();
		 
		return gFile.getId();
	
	}

    public void login(String code) throws IOException{
    drive=	authorisationHelper.getDrive(code);	
    }
	
	public AuthorisationHelper getAuthorisationHelper() {
		return authorisationHelper;
	}


}
