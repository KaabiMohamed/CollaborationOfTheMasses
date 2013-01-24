package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import persistence.Message;
import persistence.User;
@Stateless
@WebService(endpointInterface="services.MessageDAOImplRemote",serviceName="NewMessageService")
public class MessageDAOImpl implements MessageDAOImplRemote, MessageDAOImplLocal {
	@PersistenceContext (unitName = "CoM") 
	private EntityManager entityManager;

	@WebMethod(operationName="sendMessage")
	public void createMessage(Message message) {
		// TODO Auto-generated method stub
		entityManager.persist(message);
		
	}

	
	public Message findMessage(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Message.class, id);
	}

	@Override
	public void updateMessage(Message message) {
		// TODO Auto-generated method stub
		entityManager.merge(message);
		
	}

	@Override
	public void removeChallenge(Message message) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.merge(message));
		
	}

	@Override
	public List<Message> findAllMessages() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select m from Message m").getResultList();
	}


	@Override
	@WebMethod(operationName="inbox")
	public List<Message> findAllInBoxMessages(User u) {
		Query query = entityManager.createQuery("select m from Message m where m.receptor.login=:param ");
		query.setParameter("param", u.getLogin());
		return query.getResultList();
		
	}


	@Override
	@WebMethod(operationName="sendBox")
	public List<Message> findAllSendBoxMessages(User u) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select m from Message m where m.sender.login=:param");
		query.setParameter("param", u.getLogin());
		return query.getResultList();
	}
	
	

}
