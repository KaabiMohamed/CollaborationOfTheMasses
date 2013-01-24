package managedBean;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.component.panelgrid.PanelGrid;

import persistence.Challenge;
import persistence.User;

import services.ChallengeDAOImplRemote;

@ManagedBean(name="Chlngmanager")
@SessionScoped
public class ManageChallenges {
	
	@EJB
	ChallengeDAOImplRemote remote;
	Challenge challenge = new Challenge();
	private Challenge selectedChallenge;
	
	
	public Challenge getSelectedChallenge() {
		
		return selectedChallenge;
	}

	public void setSelectedChallenge(Challenge selectedChallenge) {
		this.selectedChallenge = selectedChallenge;
	}

	public DataModel getDatamodel() {
		datamodel.setWrappedData(remote.findAllChallenges());
		return datamodel;
	}

	public void setDatamodel(DataModel datamodel) {
		this.datamodel = datamodel;
	}

	public DataModel datamodel = new ListDataModel();


	public ManageChallenges() {
		
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

   public void create(ActionEvent event){
	    
	   remote.createChallenge(challenge);
   }
 
   

}
