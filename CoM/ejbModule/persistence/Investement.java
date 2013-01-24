package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table ( name = "t_investement")
public class Investement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5762213767572911335L;
	private int id;
	private float value;
	private User invester;
	private Project project;
	private Date dateInvestement;
	@Temporal (TemporalType.DATE)
	@Column (name = "inv_date")
	public Date getDate() {
		return dateInvestement;
	}
	public void setDate(Date date) {
		this.dateInvestement = date;
	}
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	@ManyToOne
	public User getInvester() {
		return invester;
	}
	public void setInvester(User invester) {
		this.invester = invester;
	}
	@ManyToOne
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Investement [id=" + id + ", value=" + value + ", invester="
				+ invester + ", project=" + project + "]";
	}
	public Investement(int id, float value, User invester, Project project) {
		super();
		this.id = id;
		this.value = value;
		this.invester = invester;
		this.project = project;
	}
	public Investement() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateInvestement == null) ? 0 : dateInvestement.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((invester == null) ? 0 : invester.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + Float.floatToIntBits(value);
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
		Investement other = (Investement) obj;
		if (dateInvestement == null) {
			if (other.dateInvestement != null)
				return false;
		} else if (!dateInvestement.equals(other.dateInvestement))
			return false;
		if (id != other.id)
			return false;
		if (invester == null) {
			if (other.invester != null)
				return false;
		} else if (!invester.equals(other.invester))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}
	
	

}
