package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import persistence.User;
import services.UserDAOImplRemote;


@ManagedBean(name = "manageUsers")
@SessionScoped
public class Users {

	//injection of the proxy
	@EJB
	UserDAOImplRemote Ruser;
	
	//model
	private User newUser = new User();
	private List<User> users = new ArrayList<User>();
	private List<User> filteredU ;
	private User selectedU;
	private User user;
	
	//methods
	
	public String create(ActionEvent event){
		Ruser.createUser(newUser);
		return "create";
	}
	
	public String update(ActionEvent event){
	    Ruser.updateUser(user);
		return "update";
	}
	public String delete(ActionEvent event){
		Ruser.removeUser(user);
		return "delete";
	}
	
	
	
	
	
	
	//Getters & Setters
	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}



	public List<User> getUsers() {
		return Ruser.findAllUsers();
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	



	public Users() {
	}

	public User getSelectedU() {
		return selectedU;
	}

	public void setSelectedU(User selectedU) {
		this.selectedU = selectedU;
	}

	public List<User> getFilteredU() {
		return filteredU;
	}

	public void setFilteredU(List<User> filteredU) {
		this.filteredU = filteredU;
	}

}
