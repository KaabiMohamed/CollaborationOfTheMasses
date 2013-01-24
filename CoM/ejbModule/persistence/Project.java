package persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_project")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3116452758057145447L;

	private int id;
	private String title;
	private String category;
	private String description;
	private User creator;
	private String joinedFile;
	private String avatarLink;
	private boolean valid;
	private float amount;
	private Date dateProject;
	private int DeadLine;
	private List<Investement> investements;

	@Temporal(TemporalType.DATE)
	@Column(name = "pro_date")
	public Date getDate() {
		return dateProject;
	}

	public void setDate(Date date) {
		this.dateProject = date;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getJoinedFile() {
		return joinedFile;
	}

	public void setJoinedFile(String joinedFile) {
		this.joinedFile = joinedFile;
	}

	public String getAvatarLink() {
		return avatarLink;
	}

	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDateProject() {
		return dateProject;
	}

	public void setDateProject(Date dateProject) {
		this.dateProject = dateProject;
	}

	public int getDeadLine() {
		return DeadLine;
	}

	public void setDeadLine(int deadLine) {
		DeadLine = deadLine;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", description=" + description
				+ ", creator=" + creator + "]";
	}

	public Project(int id, String description, User creator) {
		super();
		this.id = id;
		this.description = description;
		this.creator = creator;
	}

	public Project() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((dateProject == null) ? 0 : dateProject.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
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
		Project other = (Project) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (dateProject == null) {
			if (other.dateProject != null)
				return false;
		} else if (!dateProject.equals(other.dateProject))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@OneToMany(mappedBy = "project")
	public List<Investement> getInvestements() {
		return investements;
	}

	public void setInvestements(List<Investement> investements) {
		this.investements = investements;
	}

}
