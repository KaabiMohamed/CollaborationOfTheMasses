package managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import persistence.Challenge;
import services.ChallengeDAOImplRemote;
@ManagedBean(name="updateChallenge")
@SessionScoped
public class UpdateChallenge {
	
	@EJB
	private ChallengeDAOImplRemote Rchallenge;
	private Challenge challenge;
	private List<Challenge> listChallenge;
	private Challenge selectedRow;

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public List<Challenge> getListChallenge() {
		listChallenge=Rchallenge.findAllChallenges();
		return listChallenge;
	}

	public void setListChallenge(List<Challenge> listChallenge) {
		this.listChallenge = listChallenge;
	}

	public Challenge getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(Challenge selectedRow) {
		this.selectedRow = selectedRow;
	}

	public UpdateChallenge() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(ActionEvent event){
		Rchallenge.updateChallenge(selectedRow);
	}

}
