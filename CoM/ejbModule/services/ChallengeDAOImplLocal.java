package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Challenge;
import persistence.User;

@Local
public interface ChallengeDAOImplLocal {
	
	void createChallenge(Challenge challenge);
	Challenge findChallenge(int id);
	void updateChallenge(Challenge challenge);
	void removeChallenge(Challenge challenge);
	List<Challenge> findAllChallenges();
	List<Challenge> findAllChallengesByUser(User u);
	

}
