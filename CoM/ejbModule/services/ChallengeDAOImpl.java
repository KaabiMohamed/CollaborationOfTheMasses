package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Challenge;
import persistence.User;

@Stateless
@WebService(endpointInterface = "services.ChallengeDAOImplRemote", serviceName = "NewChallengeService")
public class ChallengeDAOImpl implements ChallengeDAOImplRemote,
		ChallengeDAOImplLocal {
	@PersistenceContext(unitName = "CoM")
	private EntityManager entityManager;

	public void createChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		entityManager.persist(challenge);

	}

	public Challenge findChallenge(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Challenge.class, id);

	}

	public void updateChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		entityManager.merge(challenge);

	}

	public void removeChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.merge(challenge));

	}

	public List<Challenge> findAllChallenges() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select c from Challenge c")
				.getResultList();

	}

	@Override
	@WebMethod(operationName = "findAllchallengesByUser")
	@WebResult(name = "challenges")
	public List<Challenge> findAllChallengesByUser(
			@WebParam(name = "user") User u) {
		// TODO Auto-generated method stub
		Query query = entityManager
				.createQuery("select c from Challenge c where c.submitter.login=:param");
		query.setParameter("param", u.getLogin());
		return query.getResultList();

	}

	@Override
	public List<Challenge> findChallengeByValid(boolean state) {
		Query query = entityManager
				.createQuery("select c from Challenge c where c.valid=:valid");
		query.setParameter("valid", state);
		return query.getResultList();
	}

	public void validateChallenge(int id) {
		Challenge c = entityManager.find(Challenge.class, id);
		c.setValid(true);
		entityManager.merge(c);

	}

	@Override
	public List<String> challengeDescriptions() {
		return entityManager.createQuery(
				"select c.description from Challenge c").getResultList();

	}

	@Override
	public String create(Challenge challenge) {
		entityManager.persist(challenge);
		return "create";
	}

}
