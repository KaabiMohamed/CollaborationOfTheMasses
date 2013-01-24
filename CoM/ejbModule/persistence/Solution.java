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
@Table(name = "t_solution")
public class Solution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3086397034464386242L;
	private int id;
	private String description;
	private User solver;
	private Challenge challenge;
	private Date dateSolution;
	private String fileName;
	private int feedBack;
	private boolean awarded;

	@Temporal(TemporalType.DATE)
	@Column(name = "sol_date")
	public Date getDate() {
		return dateSolution;
	}

	public void setDate(Date date) {
		this.dateSolution = date;
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
	public User getSolver() {
		return solver;
	}

	public void setSolver(User solver) {
		this.solver = solver;
	}

	@ManyToOne
	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	@Override
	public String toString() {
		return "Solution [id=" + id + ", description=" + description
				+ ", solver=" + solver + ", challenge=" + challenge + "]";
	}

	public Solution(int id, String description, User solver, Challenge challenge) {
		super();
		this.id = id;
		this.description = description;
		this.solver = solver;
		this.challenge = challenge;

	}

	public Solution() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((challenge == null) ? 0 : challenge.hashCode());
		result = prime * result
				+ ((dateSolution == null) ? 0 : dateSolution.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((solver == null) ? 0 : solver.hashCode());
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
		Solution other = (Solution) obj;
		if (challenge == null) {
			if (other.challenge != null)
				return false;
		} else if (!challenge.equals(other.challenge))
			return false;
		if (dateSolution == null) {
			if (other.dateSolution != null)
				return false;
		} else if (!dateSolution.equals(other.dateSolution))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (solver == null) {
			if (other.solver != null)
				return false;
		} else if (!solver.equals(other.solver))
			return false;
		return true;
	}

	public boolean isAwarded() {
		return awarded;
	}

	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}

	public int getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(int feedBack) {
		this.feedBack = feedBack;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
