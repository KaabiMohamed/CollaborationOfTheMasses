package managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import persistence.Challenge;
import persistence.User;
import services.ChallengeDAOImplRemote;
import services.UserDAOImplRemote;

@ManagedBean(name = "Challenges")
@SessionScoped
public class Challenges {

	@EJB
	UserDAOImplRemote Ruser;
	@EJB
	ChallengeDAOImplRemote Rchallenge;

	private Challenge challenge;
	
	private List<Challenge> listChallenge;
	private List<Challenge> filteredCh;
	private Challenge selectedRow;
	private Challenge newChallenge = new Challenge();

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public List<Challenge> getListChallenge() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		User current = (User) ec.getSessionMap().get("user");
		listChallenge = Rchallenge.findAllChallengesByUser(current);
		return listChallenge;
	}

	public void setListChallenge(List<Challenge> listChallenge) {
		this.listChallenge = listChallenge;
	}

	public List<Challenge> getFilteredCh() {
		return filteredCh;
	}

	public void setFilteredCh(List<Challenge> filteredCh) {
		this.filteredCh = filteredCh;
	}

	private Challenge c = null;

	public Challenge getC() {
		return c;
	}

	public void setC(Challenge c) {
		this.c = c;
	}

	public Challenge getSelectedRow() {
		System.out.println("ooooooooooooooooooooooooooooooo1" + selectedRow);
		
	

		return selectedRow;
	}

	public void setSelectedRow(Challenge selectedRow) {
		this.selectedRow = selectedRow;
	}

	public Challenge getNewChallenge() {
		return newChallenge;
	}

	public void setNewChallenge(Challenge newChallenge) {
		this.newChallenge = newChallenge;
	}

	public Challenges() {
		// TODO Auto-generated constructor stub
	}

	public String Create(ActionEvent event) {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		User current = (User) ec.getSessionMap().get("user");
		newChallenge.setSubmitter(current);
		return Rchallenge.create(newChallenge);
	}

	public String update(ActionEvent event) {
		System.out.println("++++++++++"+c);
		Rchallenge.updateChallenge(c);
		return "";
	}

	public String delete(ActionEvent event) {
		Rchallenge.removeChallenge(selectedRow);
		return "";
	}

}
