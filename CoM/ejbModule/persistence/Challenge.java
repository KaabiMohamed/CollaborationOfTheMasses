package persistence;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table ( name = "t_challenge")
public class Challenge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5203497594110701454L;
	private int id;
	private String description;
	private String award;
	private boolean valid;
	private User submitter;
	private Date chlngDate;
	private int deadLine;

	//private List<Solution> solutions;
	
	@Column (name = "chlng_date")
	@Temporal (TemporalType.DATE)
	public Date getDate() {
		return chlngDate;
	}
	public void setDate(Date date) {
		this.chlngDate = date;
	}
	@Id @GeneratedValue
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
	public User getSubmitter() {
		return submitter;
	}
	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}
	
	@Override
	public String toString() {
		return "Challenge [id=" + id + ", description=" + description
				+ ", submitter=" + submitter + "]";
	}
	public Challenge(int id, String description, User submitter, Date d,int deadLine , boolean valid) {
		super();
		this.id = id;
		this.description = description;
		this.submitter = submitter;
		this.chlngDate = d;
		this.deadLine = deadLine;
		this.valid = valid;
	}
	public Challenge() {
		super();
	
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((submitter == null) ? 0 : submitter.hashCode());
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
		Challenge other = (Challenge) obj;
		if (chlngDate == null) {
			if (other.chlngDate != null)
				return false;
		} else if (!chlngDate.equals(other.chlngDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (submitter == null) {
			if (other.submitter != null)
				return false;
		} else if (!submitter.equals(other.submitter))
			return false;
		return true;
	}
//	@ONETOMANY ( MAPPEDBY = "CHALLENGE")
//	PUBLIC LIST<SOLUTION> GETSOLUTIONS() {
//		RETURN SOLUTIONS;
//	}
//	PUBLIC VOID SETSOLUTIONS(LIST<SOLUTION> SOLUTIONS) {
//		THIS.SOLUTIONS = SOLUTIONS;
//	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public int getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(int deadLine) {
		this.deadLine = deadLine;
	}
	
	

}
