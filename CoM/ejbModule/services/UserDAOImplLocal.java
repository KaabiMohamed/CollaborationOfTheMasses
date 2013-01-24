package services;

import java.util.List;

import javax.ejb.Local;

import persistence.User;

@Local
public interface UserDAOImplLocal {
	void createUser(User user);
	User findUser(String login);
	void updateUser(User user);
	void removeUser(User user);
	List<User> findAllUsers();

	
}
