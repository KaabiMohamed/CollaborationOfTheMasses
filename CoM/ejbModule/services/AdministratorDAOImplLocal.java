package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Administrator;

@Local
public interface AdministratorDAOImplLocal {
	void createAdministrator(Administrator administrator);
	Administrator findAdministrator(String login);
	void updateAdministrator(Administrator administrator);
	void removeAdministrator(Administrator administrator);
	List<Administrator> findAllAdministrators();

}
