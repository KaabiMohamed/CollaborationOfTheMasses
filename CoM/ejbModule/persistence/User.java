package persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table ( name = "t_user")
public class User implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8130539738036460823L;
	private String login;
	private String passWord;
	private String firstName;
	private String lastName;
	private String description;
	private String skills ;
	private String interests;
	private String expericence;
//	private List<Project> projects;
//	private List<Challenge> challenges;
//	private List<Investement> investements;
//	private List<Solution> solutions;
//	private List<FeedBack> feedBacks;
//	private List<Message> inBox;
//	private List<Message> sendBox;
	private Date registerDate;
	

	@Id
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getExpericence() {
		return expericence;
	}

	public void setExpericence(String expericence) {
		this.expericence = expericence;
	}

	

	public User(String login, String passWord, String firstName,
			String lastName, String description, String skills,
			String interests, String expericence, List<Message> inBox,
			List<Message> sendBox, Date registerDate) {
		super();
		this.login = login;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.skills = skills;
		this.interests = interests;
		this.expericence = expericence;
//		this.inBox = inBox;
//		this.sendBox = sendBox;
		this.registerDate = registerDate;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((expericence == null) ? 0 : expericence.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((interests == null) ? 0 : interests.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((passWord == null) ? 0 : passWord.hashCode());
		result = prime * result
				+ ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expericence == null) {
			if (other.expericence != null)
				return false;
		} else if (!expericence.equals(other.expericence))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (interests == null) {
			if (other.interests != null)
				return false;
		} else if (!interests.equals(other.interests))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", passWord=" + passWord
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", description=" + description + ", skills=" + skills
				+ ", interests=" + interests + ", expericence=" + expericence
				+ ", registerDate=" + registerDate + "]";
	}

	public User() {
		super();
	}
//	@OneToMany ( mappedBy = "submitter" )
//	public List<Challenge> getChallenges() {
//		return challenges;
//	}
//
//	public void setChallenges(List<Challenge> challenges) {
//		this.challenges = challenges;
//	}
//	@OneToMany ( mappedBy = "invester" )
//	public List<Investement> getInvestements() {
//		return investements;
//	}
//
//	public void setInvestements(List<Investement> investements) {
//		this.investements = investements;
//	}
//	@OneToMany ( mappedBy = "solver")
//	public List<Solution> getSolutions() {
//		return solutions;
//	}
//
//	public void setSolutions(List<Solution> solutions) {
//		this.solutions = solutions;
//	}
//	@OneToMany ( mappedBy = "user")
//	public List<FeedBack> getFeedBacks() {
//		return feedBacks;
//	}
//
//	public void setFeedBacks(List<FeedBack> feedBacks) {
//		this.feedBacks = feedBacks;
//	}
//	@OneToMany ( mappedBy = "receptor")
//	public List<Message> getInBox() {
//		return inBox;
//	}
//	
//	public void setInBox(List<Message> inBox) {
//		this.inBox = inBox;
//	}
//	@OneToMany ( mappedBy = "sender")
//	public List<Message> getSendBox() {
//		return sendBox;
//	}
//
//	public void setSendBox(List<Message> sendBox) {
//		this.sendBox = sendBox;
//	}
//	@OneToMany (mappedBy = "creator")
//	public List<Project> getProjects() {
//		return projects;
//	}
//
//	public void setProjects(List<Project> projects) {
//		this.projects = projects;
//	}
	@Temporal (TemporalType.DATE)
	@Column (name = "register_date")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDAte) {
		this.registerDate = registerDAte;
	}
	
	
	

}
