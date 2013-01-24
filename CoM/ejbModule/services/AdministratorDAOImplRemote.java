package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Administrator;



@Remote
public interface AdministratorDAOImplRemote {
	
	void createAdministrator(Administrator administrator);
	Administrator findAdministrator(String login);
	void updateAdministrator(Administrator administrator);
	void removeAdministrator(Administrator administrator);
	List<Administrator> findAllAdministrators();
	
	

}
