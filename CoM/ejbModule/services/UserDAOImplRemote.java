package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import persistence.User;

@Remote
@WebService (name="Users")
@SOAPBinding (style=Style.DOCUMENT , parameterStyle=ParameterStyle.WRAPPED , use=Use.LITERAL)
public interface UserDAOImplRemote {
	void createUser(User user);
	
	@WebMethod(operationName="findUser")
	User findUser(String login);
	
    @WebMethod(operationName="updateUser")
	void updateUser(User user);
	 
	void removeUser(User user);
	
	List<User> findAllUsers();

}
