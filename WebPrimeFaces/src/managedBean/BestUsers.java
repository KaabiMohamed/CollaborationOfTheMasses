package managedBean;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import persistence.Message;
import persistence.Solution;
import persistence.User;

import services.MessageDAOImplRemote;
import services.SolutionDAOImplRemote;

@ManagedBean(name = "BestUsers")
@SessionScoped
public class BestUsers {
	
	private User selectedUser = new User();

	@EJB 
	SolutionDAOImplRemote remote;
	
	@EJB
	MessageDAOImplRemote msgRemote;
	
	private List<Solution> solutions = new ArrayList<Solution>();
	
	private User bestUser = new User();

	private DataModel dataModel  = new ListDataModel();
	
	private String nbAward;
	
	private Message message = new Message();
	
	private List<User> bestUsers = new ArrayList<User>();
	
	private DataModel dataModel2  = new ListDataModel();
	
	public User getBestUser() {
		return bestUser;
	}

	public void setBestUser(User bestUser) {
		this.bestUser = bestUser;
	}
	
	public void sendMessage(){
		message.setDate(new Date());
		message.setReceptor(selectedUser);
		message.setSender((User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
		msgRemote.createMessage(message);
	}
	
	@PostConstruct
	public void findBestUser(){
		List<Solution> solutions = remote.findAllSolutions();
		List<Solution> awarded = new ArrayList<Solution>();
		Iterator<Solution> i = solutions.iterator();
		while(i.hasNext()){
			Solution s = i.next();
			if(s.isAwarded()) awarded.add(s);
		}
		List<User> usersAwarded = new ArrayList<User>();
		i = awarded.iterator();
		
		while(i.hasNext()){
			User u = i.next().getSolver();
			if(!usersAwarded.contains(u)) {usersAwarded.add(u);System.out.println("->"+u.getLogin());}
		}
		List<Integer> aws = new ArrayList<Integer>();
		
		for(User u : usersAwarded){
			int z = 0;
			for(Solution s : awarded){
				if(s.getSolver().getLogin().equals(u.getLogin())) z++;
			}
			aws.add(z);
			System.out.println("*****"+u.getLogin()+"****"+z);
		}
		
		int pos = 0;
		System.out.println("°°°°°°°°°°°°°"+aws.get(0));
		Integer maxCon = aws.get(0);
		for(Integer integer : aws){
			if(integer>maxCon) {maxCon = integer;pos = aws.indexOf(integer);System.out.println("########"+pos);}
		}
			
		
		bestUser =usersAwarded.get(pos);
		this.setNbAward(aws.get(pos));
		
		findBestUsers();
		
		
	}
	
	public void findBestUsers(){
		
			List<Solution> solutions = remote.findAllSolutions();
			List<Solution> awarded = new ArrayList<Solution>();
			Iterator<Solution> i = solutions.iterator();
			while(i.hasNext()){
				Solution s = i.next();
				if(s.isAwarded()) awarded.add(s);
			}
			List<User> usersAwarded = new ArrayList<User>();
			i = awarded.iterator();
			
			while(i.hasNext()){
				User u = i.next().getSolver();
				if(!usersAwarded.contains(u)) {usersAwarded.add(u);System.out.println("->"+u.getLogin());}
			}
			List<Integer> aws = new ArrayList<Integer>();
			
			for(User u : usersAwarded){
				int z = 0;
				for(Solution s : awarded){
					if(s.getSolver().getLogin().equals(u.getLogin())) z++;
				}
				aws.add(z);
				System.out.println("*****"+u.getLogin()+"****"+z);
			}
			boolean v;
			do{
				v=false;
				for(int c=0;c<aws.size()-1;c++){
					if(aws.get(c)<aws.get(c+1)){
					Integer aux=aws.get(c);
					aws.set(c, aws.get(c+1));
					aws.set(c+1,aux);
					User uaux = usersAwarded.get(c);
					usersAwarded.set(c, usersAwarded.get(c+1));
					usersAwarded.set(c+1,uaux);
					System.out.println("Changé");
					v = true;
				}
				}
				
			}while(v==true);
			if(usersAwarded.size()>=5)
			bestUsers = usersAwarded.subList(0, 5);
			else bestUsers = usersAwarded.subList(0, usersAwarded.size());
			
		}
	
	
	
	
	public DataModel getDataModel() {
		solutions=remote.findallSolutionsByUser(bestUser);
		dataModel .setWrappedData(solutions);
		return dataModel;
	}

	public void setDataModel(DataModel<Solution> dataModel) {
		this.dataModel = dataModel;
	}
	
	public List<Solution> getSolutions() {
		return solutions;
	}
	
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	public DataModel getDataModel2() {
		dataModel2.setWrappedData(bestUsers);
		return dataModel2;
	}

	public void setDataModel2(DataModel dataModel2) {
		this.dataModel2 = dataModel2;
	}

	public List<User> getBestUsers() {
		return bestUsers;
	}

	public void setBestUsers(List<User> bestUsers) {
		this.bestUsers = bestUsers;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public String getNbAward() {
		return nbAward;
	}

	public void setNbAward(int nbAward) {
		this.nbAward = String.valueOf(nbAward);
	}
	
}
