package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import persistence.Challenge;
import persistence.Solution;
import persistence.User;


@Stateless
public class SolutionDAOImpl implements SolutionDAOImplRemote,SolutionDAOImplLocal{
	
	@PersistenceContext (unitName = "CoM") 
	private EntityManager entityManager;

	public void createSolution(Solution solution) {
		// TODO Auto-generated method stub
		entityManager.persist(solution);
		
	}

	
	public Solution findSolution(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Solution.class, id);
	}

	
	public void updateSolution(Solution solution) {
		// TODO Auto-generated method stub
		entityManager.merge(solution);
	}

	
	public void removeSolution(Solution solution) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.merge(solution));
	}

	
	public List<Solution> findAllSolutions() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select s from Solution s").getResultList();
	}


	@Override
	public List<Solution> findallSolutionsByChallenge(Challenge c) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select s from Solution s where s.challenge.id=:param");
		query.setParameter("param", c.getId());
		return query.getResultList();
	}


	@Override
	public List<Solution> findallSolutionsByUser(User u) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select s from Solution s where s.solver.login=:param");
		query.setParameter("param", u.getLogin());
		return query.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Solution> findBestSolutionsByChallenge(Challenge c) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select s from Solution s where s.challenge.id=:param and s.feedBack >= :param2");
		query.setParameter("param", c.getId());
		query.setParameter("param2", 4);
		return query.getResultList();
	}
	



	
	
}
