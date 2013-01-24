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

import persistence.Message;
import persistence.User;
import services.MessageDAOImplRemote;
import services.UserDAOImplRemote;

@ManagedBean(name = "Messages")
@SessionScoped
public class Messages {

	// model
	private List<Message> inbox;
	private List<Message> sendbox;
	private Message selectedMsg;
	private Message newMessage = new Message();
	private Message replyMessage = new Message();
	private List<Message> filtredMsg;
	User currentUser;
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// Calling session

	// proxy push
	@EJB
	MessageDAOImplRemote MsgRemote;
	@EJB
	UserDAOImplRemote Ruser;

	// Methods

	public String NewMessage(ActionEvent event) {

		newMessage.setSender(currentUser);
		newMessage.setReceptor(Ruser.findUser(login));
		newMessage.setDate(new Date());
		MsgRemote.createMessage(newMessage);
		return "";
	}

	public String Reply(ActionEvent event) {

		User newReceptor = selectedMsg.getSender();
		replyMessage.setSender(currentUser);
		replyMessage.setReceptor(newReceptor);

		replyMessage.setDate(new Date());
		MsgRemote.createMessage(replyMessage);
		return "";
	}

	public String delete(ActionEvent event) {
		MsgRemote.removeChallenge(selectedMsg);
		return "";
	}

	public Messages() {
	}

	// Getters And Setters
	public List<Message> getInbox() {

		inbox = MsgRemote.findAllInBoxMessages(currentUser);
		return inbox;
	}

	public void setInbox(List<Message> inbox) {
		this.inbox = inbox;
	}

	public List<Message> getSendbox() {

		sendbox = MsgRemote.findAllSendBoxMessages(currentUser);
		return sendbox;
	}

	public void setSendbox(List<Message> sendbox) {
		this.sendbox = sendbox;
	}

	public Message getSelectedMsg() {
		return selectedMsg;
	}

	public void setSelectedMsg(Message selectedMsg) {
		this.selectedMsg = selectedMsg;
	}

	public Message getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(Message newMessage) {
		this.newMessage = newMessage;
	}

	public List<Message> getFiltredMsg() {
		return filtredMsg;
	}

	public void setFiltredMsg(List<Message> filtredMsg) {
		this.filtredMsg = filtredMsg;
	}

	public Message getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(Message replyMessage) {
		this.replyMessage = replyMessage;
	}

	@PostConstruct
	public void initUser() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		currentUser = (User) ec.getSessionMap().get("user");
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
