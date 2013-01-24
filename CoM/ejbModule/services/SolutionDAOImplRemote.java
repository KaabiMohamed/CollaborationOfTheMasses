package services;


import java.util.List;

import javax.ejb.Remote;



import persistence.Challenge;
import persistence.Solution;
import persistence.User;
@Remote
public interface SolutionDAOImplRemote {
	void createSolution(Solution solution);
	Solution findSolution(int id);
	void updateSolution(Solution solution);
	void removeSolution(Solution solution);
	List<Solution> findAllSolutions();
	List<Solution> findallSolutionsByChallenge(Challenge c);
	List<Solution> findallSolutionsByUser(User u);
	List<Solution> findBestSolutionsByChallenge(Challenge c);
	
}
