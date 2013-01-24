package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.RateEvent;

import persistence.Challenge;
import persistence.Solution;
import services.ChallengeDAOImplRemote;
import services.SolutionDAOImplRemote;

@ManagedBean(name = "Final")
@SessionScoped
public class FinalSolution {

	@EJB
	SolutionDAOImplRemote remote;

	@EJB
	ChallengeDAOImplRemote remoteChallenge;

	private Solution solution = new Solution();
	private List<Solution> solutions = new ArrayList<Solution>();
	private DataModel<Solution> dataModel = new ListDataModel<Solution>();
	private DataModel<Challenge> dataModel2 = new ListDataModel<Challenge>();
	private List<Challenge> challenges = new ArrayList<Challenge>();
	private List<Challenge> filteredCh;
	private Challenge selectedRow;

	private Solution selecSol = new Solution();

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	public DataModel<Solution> getDataModel() {
		if (selectedRow != null) {
			System.out.println("chargé");
			solutions = remote.findBestSolutionsByChallenge(selectedRow);
			dataModel.setWrappedData(solutions);
		} else {
			solutions = new ArrayList<Solution>();
		}

		return dataModel;
	}

	public void setDataModel(DataModel<Solution> dataModel) {
		this.dataModel = dataModel;
	}

	public List<Challenge> getChallenges() {
		challenges = remoteChallenge.findChallengeByValid(true);
		return challenges;
	}

	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}

	public List<Challenge> getFilteredCh() {
		return filteredCh;
	}

	public void setFilteredCh(List<Challenge> filteredCh) {
		this.filteredCh = filteredCh;
	}

	public Challenge getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(Challenge selectedRow) {

		System.out.println(selectedRow);

		this.selectedRow = selectedRow;
	}

	public String laSolutionFinale() {
		selecSol = dataModel.getRowData();
		System.out.println(dataModel.getRowCount());
		selecSol.setAwarded(true);
		remote.updateSolution(selecSol);
		return "";

	}

	public DataModel<Challenge> getDataModel2() {
		dataModel2.setWrappedData(remoteChallenge.findAllChallenges());
		return dataModel2;
	}

	public void setDataModel2(DataModel<Challenge> dataModel2) {
		this.dataModel2 = dataModel2;
	}

}
