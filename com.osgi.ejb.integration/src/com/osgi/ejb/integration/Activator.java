package com.osgi.ejb.integration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.osgi.ejb.integration.service.GoogleDriveService;
import com.osgi.ejb.integration.service.GoogleDriveServiceImpl;

public class Activator implements BundleActivator {

	public ServiceRegistration registration;
	public void start(BundleContext context) throws Exception {
		registration=context.registerService(GoogleDriveService.class.getName(),new GoogleDriveServiceImpl() ,null );
		System.out.println("Starting ------  GoogleDrive Client.....");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Google drive OFF !!");
	}

}
