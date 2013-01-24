package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import persistence.Challenge;
import persistence.User;

@Remote
@WebService (name="Challenges")
@SOAPBinding (style=Style.DOCUMENT , parameterStyle=ParameterStyle.WRAPPED , use=Use.LITERAL)
public interface ChallengeDAOImplRemote {
	
	String create(Challenge challenge);
	void createChallenge(Challenge challenge);
	@WebMethod(operationName="findAllchallengesById")
	@WebResult(name="challenge")
	Challenge findChallenge(@WebParam(name="id")int id);
	void updateChallenge(Challenge challenge);
	void removeChallenge(Challenge challenge);
	List<Challenge> findAllChallenges();
	@WebMethod(operationName="findAllchallengesByUser")
	@WebResult(name="challenges")
	List<Challenge> findAllChallengesByUser(@WebParam(name="user")User u);
	List<Challenge> findChallengeByValid(boolean valid);
	void validateChallenge(int id);
	List<String> challengeDescriptions();
	

}
