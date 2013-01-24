package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Administrator;

/**
 * Session Bean implementation class AdministratorDAOImpl
 */
@Stateless
public class AdministratorDAOImpl implements AdministratorDAOImplRemote, AdministratorDAOImplLocal {
	@PersistenceContext (unitName = "CoM") 
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public AdministratorDAOImpl() {
        
    }

	
	public void createAdministrator(Administrator administrator) {
		
		entityManager.persist(administrator);
		
	}

	
	public Administrator findAdministrator(String login) {
		return entityManager.find(Administrator.class, login);
		
	}

	
	public void updateAdministrator(Administrator administrator) {
		entityManager.merge(administrator);
			}

	
	public void removeAdministrator(Administrator administrator) {
		entityManager.remove(entityManager.merge(administrator));
		
	}

	
	public List<Administrator> findAllAdministrators() {
		return entityManager.createQuery("select a from Administrator a").getResultList();
	}

}
