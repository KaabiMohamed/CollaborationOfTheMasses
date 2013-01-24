package com.osgi.ejb.integration.service;

import java.io.IOException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;


public class AuthorisationHelper {

	private static AuthorisationHelper INSTANCE;
	public static String CLIENT_ID = "1071135575064.apps.googleusercontent.com";
	public static String CLIENT_SECRET = "g1Y7oMsgjun21HlPrJQiE0dV";
	public static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	
    HttpTransport httpTransport;
    JsonFactory jsonFactory;
    GoogleAuthorizationCodeFlow flow;
    private Drive service;
    
	public String getVerificationUrl(){
		 String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		 return url;
	}
	
	
	public Drive getDrive(String code) throws IOException{
		  if(service==null)  {
	 	   GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
	 	    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
	 	    //Create a new authorized API client
	 	    service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
		  }
	 	    return service;
	}
	
	private AuthorisationHelper() {
		httpTransport = new NetHttpTransport();
    	jsonFactory = new JacksonFactory();
    	flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
         .setAccessType("online")
         .setApprovalPrompt("auto").build();
    	
	}


	public static AuthorisationHelper getINSTANCE() {
		if(INSTANCE==null)
		INSTANCE=new AuthorisationHelper();
		return INSTANCE;
	}
	
}
