package managedBean;

import java.util.ArrayList;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import persistence.Administrator;
import persistence.User;
import services.AdministratorDAOImplRemote;
import services.UserDAOImplRemote;





@ManagedBean(name="manager")
@SessionScoped
public class ManageAdministrators {
	
	
	
	@EJB
	AdministratorDAOImplRemote remote;
	Administrator administrator = new Administrator() ;
	User user = new User();
	UserDAOImplRemote usr ;
	private List<Administrator> admins = new ArrayList<Administrator>();
	public DataModel datamodel = new ListDataModel();
	private Administrator selectedRow;
	private String NewPwd;
	
	
	
	public Administrator getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(Administrator selectedRow) {
		this.selectedRow = selectedRow;
	}

	public DataModel<Administrator> getDatamodel() {
		admins=remote.findAllAdministrators();
		datamodel.setWrappedData(admins);
		return datamodel;
	}

	public void setDatamodel(DataModel<Administrator> datamodel) {
	
		this.datamodel = datamodel;
	}


	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}



	public ManageAdministrators() {

	}
	
	public void create(ActionEvent event){
		
		
		remote.createAdministrator(administrator);
		//Administrator administrator = new Administrator();
	}
	public void delete(ActionEvent event){
		
		remote.removeAdministrator(selectedRow);
		
	}
	public void update(ActionEvent event){
		System.out.println(NewPwd);
		String OldPwd = selectedRow.getPassWord();
		if (OldPwd.toString().equals(NewPwd.toString()) ){
			 System.out.println("pas de changement");
		 }
		 else {
			 selectedRow.setPassWord(NewPwd);
			 remote.updateAdministrator(selectedRow);
		 }
		
		
	}

	public List<Administrator> getadmins() {
		
		return remote.findAllAdministrators();
	}

	public void setAmdins(List<Administrator> admins) {
		
		this.admins = admins;
	}

	public String getNewPwd() {
		return NewPwd;
	}

	public void setNewPwd(String newPwd) {
		NewPwd = newPwd;
	}

}
