package com.osgi.ejb.integration.EJB.SLSB;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.osgi.ejb.integration.service.GoogleDriveService;

/**
 * Session Bean implementation class GoogleDriveFacade
 */
@Stateful
@LocalBean
public class GoogleDriveFacade implements GD{

	@Resource
   BundleContext context;
	ServiceTracker serviceTracker;
    GoogleDriveService googleDriveService;
    
	@PostConstruct
	public void init(){
		
		serviceTracker=new ServiceTracker(context,GoogleDriveService.class.getName(), null);
		serviceTracker.open();
		googleDriveService=(GoogleDriveService) serviceTracker.getService();
	}
	
    public GoogleDriveFacade() {
        // TODO Auto-generated constructor stub
    }

	

	@Override
	public String getVerificationUrl() {
		return googleDriveService.getAuthorisationHelper().getVerificationUrl();
	}

	@Override
	public String testHello() {
		
		return googleDriveService.sayHello()+":\n ";
	}

	@Override
	public void login(String code) throws IOException {
		googleDriveService.login(code);
		
	}
	
	@Override
	public void uplaodFile(File file,String title, String description, String mimeType) throws IOException{
		googleDriveService.addFile(file, title, description, mimeType);
	}

	
    
    
    

}
