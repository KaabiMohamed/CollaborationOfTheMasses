package managedBean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import persistence.Challenge;
import persistence.Message;
import persistence.User;
import services.ChallengeDAOImplRemote;
import services.MessageDAOImplRemote;


@ManagedBean(name="ValidateChallenge")
@SessionScoped
public class ValidationChallenge {

	private Challenge challenge;
	@EJB
	private ChallengeDAOImplRemote Rchallenge;
	@EJB
	private MessageDAOImplRemote Rmessage;
	private Message msg = new Message();
	private List<Challenge> chlngs;
	private List<Challenge> filteredChallenges;
	private Challenge selectedRow;
	
	
	
	
	public Challenge getChallenge() {
		return challenge;
	}




	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}




	public List<Challenge> getChlngs() {
		return chlngs;
	}




	public void setChlngs(List<Challenge> chlngs) {
		this.chlngs = chlngs;
	}




	public List<Challenge> getFilteredChallenges() {
		filteredChallenges = Rchallenge.findChallengeByValid(false);
		return filteredChallenges;
	}




	public void setFilteredChallenges(List<Challenge> filteredChallenges) {
		this.filteredChallenges = filteredChallenges;
	}




	public Challenge getSelectedRow() {
		return selectedRow;
	}




	public void setSelectedRow(Challenge selectedRow) {
		this.selectedRow = selectedRow;
	}




	public ValidationChallenge() {
	}
	
	public void validate(ActionEvent event){
		Rchallenge.validateChallenge(selectedRow.getId());
		msg.setDate(new Date());
		msg.setReceptor(selectedRow.getSubmitter());
		User u = new User();
		u.setLogin("A123");
		msg.setSender(u);
		msg.setObject("Validation");
		msg.setMessage("Le challenge "+selectedRow.getDescription()+" was validated");
		Rmessage.createMessage(msg);
		
	}
	public void delete(ActionEvent event){
		Rchallenge.removeChallenge(selectedRow);
	}




	public Message getMsg() {
		return msg;
	}




	public void setMsg(Message msg) {
		this.msg = msg;
	}

}
