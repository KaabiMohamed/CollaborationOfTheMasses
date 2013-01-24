package managedBean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistence.Challenge;
import persistence.Message;
import persistence.User;
import services.MessageDAOImplRemote;
import services.UserDAOImplRemote;

@ManagedBean(name = "skills")
@SessionScoped
public class ManageSkills {

	@EJB
	UserDAOImplRemote Ruser;
	@EJB
	MessageDAOImplRemote Rmsg;

	private Message msg = new Message();
	User user;
	private List<User> users;
	private List<User> filteredU;
	private User selectedU;
	private String nomchallenge;
	private Challenge chlng;
	private String snotify;

	public List<User> getUsers() {
		users = Ruser.findAllUsers();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getFilteredU() {
		return filteredU;
	}

	public void setFilteredU(List<User> filteredU) {
		this.filteredU = filteredU;
	}

	public User getSelectedU() {
		return selectedU;
	}

	public void setSelectedU(User selectedU) {
		this.selectedU = selectedU;
	}

	@PostConstruct
	public void initUser() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		User current = (User) ec.getSessionMap().get("user");
		user = Ruser.findUser(current.getLogin());
		//System.out.println("eee7777eeeemm");
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public String getNomchallenge() {
		return nomchallenge;
	}

	public void setNomchallenge(String nomchallenge) {
		this.nomchallenge = nomchallenge;
	}

	public Challenge getChlng() {
		return chlng;
	}

	public void setChlng(Challenge chlng) {
		this.chlng = chlng;
	}

	public String getSnotify() {
		return snotify;
	}

	public void setSnotify(String snotify) {
		this.snotify = snotify;
	}

	public ManageSkills() {

	}

	public void validate(ActionEvent event) {
		System.out.println("/////////" + user);

		Ruser.updateUser(user);

	}

	public void inviting(ActionEvent event) {
        msg.setSender(user);
		msg.setReceptor(selectedU);
		msg.setObject("Invite");
		msg.setDate(new Date());
		msg.setMessage("You've Been Notified to Join this Challenge  : "
				+ nomchallenge + " So you can contact Us for more informations");
		Rmsg.createMessage(msg);
	}

	/*
	 * msg.setReceptor(selectedU);
	 * 
	 * msg.setObject("invitation");
	 * msg.setMessage("vous avez une invitation pour le challenge");
	 * Rmsg.createMessage(msg);
	 */

	public void notify(ActionEvent event) {
		msg.setReceptor(selectedU);
		// msg.setSender(selectedU);
		msg.setObject("Notification");
		msg.setDate(new Date());
		msg.setMessage("vous avez reçu une notification par rapport au sujet :"
				+ snotify + "");
		Rmsg.createMessage(msg);

	}

}