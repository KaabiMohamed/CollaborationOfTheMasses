package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.RateEvent;

import persistence.Challenge;
import persistence.User;

import persistence.Solution;

import services.ChallengeDAOImplRemote;

import services.SolutionDAOImplRemote;

@ManagedBean(name = "Rating")
@SessionScoped
public class TestRating {
	@EJB
	SolutionDAOImplRemote remote;

	@EJB
	ChallengeDAOImplRemote remoteChallenge;

	private HtmlDataTable table;
	User user;
	
	@PostConstruct
	public void initUser() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		user = (User) ec.getSessionMap().get("user");
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Solution solution = new Solution();
	private List<Solution> solutions = new ArrayList<Solution>();
	private DataModel<Solution> dataModel = new ListDataModel<Solution>();
	private DataModel<Challenge> dataModel2 = new ListDataModel<Challenge>();
	private List<Challenge> filteredCh;
	private Challenge selectedRow;

	Solution selecSol;

	public void onrate(RateEvent rateEvent) {

		selecSol = (Solution) dataModel.getRowData();
		selecSol.setFeedBack(((Integer) rateEvent.getRating()).intValue());
		
		remote.updateSolution(selecSol);
		// feedback.setSolution((Solution)dataModel.getRowData());

		// remotefeed.updateFeedBack(feedback);
	}

	public void oncancel() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Cancel Event", "Rate Reset");

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

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
			solutions = remote.findallSolutionsByChallenge(selectedRow);
			dataModel.setWrappedData(solutions);
		} else {
			solutions = new ArrayList<Solution>();
		}

		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
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

	public HtmlDataTable getTable() {
		return table;
	}

	public void setTable(HtmlDataTable table) {
		this.table = table;
	}

	public void Validate(ActionEvent event) {
		remote.updateSolution(selecSol);
	}

	public DataModel<Challenge> getDataModel2() {
		dataModel2.setWrappedData(remoteChallenge.findAllChallengesByUser(user));
		return dataModel2;
	}

	public void setDataModel2(DataModel<Challenge> dataModel2) {
		this.dataModel2 = dataModel2;
	}
	public Solution getSelecSol() {
		return selecSol;
	}
	public void setSelecSol(Solution selecSol) {
		this.selecSol = selecSol;
	}

}
