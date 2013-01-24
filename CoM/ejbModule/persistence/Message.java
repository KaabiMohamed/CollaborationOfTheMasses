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
@Table ( name = "t_message")
public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4163532867647065941L;
	private int id;
	private User receptor;
	private User sender;
	private Date dateSend;
	private String object;
	private String message;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	public User getReceptor() {
		return receptor;
	}
	public void setReceptor(User receptor) {
		this.receptor = receptor;
	}
	@ManyToOne
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	@Temporal (TemporalType.DATE)
	@Column (name = "msg_date")
	public Date getDate() {
		return dateSend;
	}
	public void setDate(Date date) {
		this.dateSend = date;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateSend == null) ? 0 : dateSend.hashCode());
		result = prime * result + id;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result
				+ ((receptor == null) ? 0 : receptor.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
		Message other = (Message) obj;
		if (dateSend == null) {
			if (other.dateSend != null)
				return false;
		} else if (!dateSend.equals(other.dateSend))
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (receptor == null) {
			if (other.receptor != null)
				return false;
		} else if (!receptor.equals(other.receptor))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", receptor=" + receptor + ", sender="
				+ sender + ", date=" + dateSend + ", object=" + object
				+ ", message=" + message + "]";
	}
	public Message(int id, User receptor, User sender, Date date,
			String object, String message) {
		super();
		this.id = id;
		this.receptor = receptor;
		this.sender = sender;
		this.dateSend = date;
		this.object = object;
		this.message = message;
	}
	public Message() {
	
	}
	
	
	
	

}
