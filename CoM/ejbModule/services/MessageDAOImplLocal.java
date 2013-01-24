package services;



import java.util.List;

import javax.ejb.Local;

import persistence.Message;
import persistence.User;


@Local
public interface MessageDAOImplLocal {
	void createMessage(Message message);
	Message findMessage(int id);
	void updateMessage(Message message);
	void removeChallenge(Message message);
	List<Message> findAllMessages();
	List<Message> findAllInBoxMessages(User u);
	List<Message> findAllSendBoxMessages(User u);
	

}
