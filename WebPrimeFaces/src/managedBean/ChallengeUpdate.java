package managedBean;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.component.panelgrid.PanelGrid;


import persistence.Administrator;
import persistence.Challenge;
import persistence.User;

import services.ChallengeDAOImplRemote;


@ManagedBean (name="updateChlng")
@SessionScoped
public class ChallengeUpdate {
	
	int IdChlng;
	@EJB
	ChallengeDAOImplRemote Rchallenge;
	//Challenge challenge = new Challenge(1, "Aaaaaaaaaaaaaaaa", null, null, 30);
	private List<Challenge> chlngs = new ArrayList<Challenge>();
	public DataModel<Challenge> datamodel = new ListDataModel<Challenge>();
	//public DataModel<Challenge> datamodelnotValid = new ListDataModel<Challenge>();
	private Challenge selectedRow;
	Challenge challenge = new Challenge();
	public List<Challenge> getChlngs() {
		return chlngs;
	}

	public void setChlngs(List<Challenge> chlngs) {
		this.chlngs = chlngs;
	}

	public DataModel<Challenge> getDatamodel() { 
		boolean valid = false;
		datamodel.setWrappedData(Rchallenge.findAllChallenges());
		return datamodel;
	}

	public void setDatamodel(DataModel<Challenge> datamodel) {
		this.datamodel = datamodel;
	}




	
	
	
	

	public Challenge getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(Challenge selectedRow) {
		this.selectedRow = selectedRow;
	}

	public ChallengeUpdate() {
		
		
	}

	public Challenge getChallenge() {
		
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge =challenge;
	}

	public int getIdChlng() {
		return IdChlng;
	}

	public void setIdChlng(int idChlng) {
		IdChlng = idChlng;
	}
	
	
	public Challenge update(ActionEvent event){
		return selectedRow;
		
	}
	public void updateChlng(ActionEvent event){
		
		Rchallenge.updateChallenge(challenge);
	}
	
	public void write(Challenge c){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File("C:\\ch.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter p = new PrintWriter(bw);
		p.println(c.toString());
		p.close();
	}
	
	
	public void validate(ActionEvent event) {
		
		challenge=selectedRow;
		int id = selectedRow.getId();
		write(selectedRow);
		Rchallenge.validateChallenge(id);
		 
	}

}
