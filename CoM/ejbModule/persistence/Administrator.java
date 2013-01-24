package persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "t_administrator")
public class Administrator implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -3813351127650465576L;
	private String login;
	private String passWord;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((passWord == null) ? 0 : passWord.hashCode());
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
		Administrator other = (Administrator) obj;
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
		return true;
	}
	@Override
	public String toString() {
		return "Administrator [login=" + login + ", passWord=" + passWord + "]";
	}
	public Administrator(String login, String passWord) {
		super();
		this.login = login;
		this.passWord = passWord;
	}
	public Administrator() {
		super();
	}
	
	

}
