package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.User;
@Stateless
@WebService(endpointInterface="services.UserDAOImplRemote",serviceName="NewUserService")
public class UserDAOImpl implements UserDAOImplRemote, UserDAOImplLocal{

	@PersistenceContext (unitName = "CoM") 
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public UserDAOImpl() {
        
    }

	
	public void createUser(User user) {
		
		entityManager.persist(user);
		
	}

	@WebMethod(operationName="findUser")
	public User findUser(String login) {
		return entityManager.find(User.class, login);
		
		
	}

	@WebMethod(operationName="updateUser")
	public void updateUser(User user) {
		entityManager.merge(user);
			}

	
	public void removeUser(User user) {
		entityManager.remove(entityManager.merge(user));
		
	}

	
	public List<User> findAllUsers() {
		return entityManager.createQuery("select u from User u").getResultList();
	}
	


}
