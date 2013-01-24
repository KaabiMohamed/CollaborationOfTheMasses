package services;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import persistence.Message;
import persistence.User;

@Remote
@WebService (name="Messages")
@SOAPBinding (style=Style.DOCUMENT , parameterStyle=ParameterStyle.WRAPPED , use=Use.LITERAL)
public interface MessageDAOImplRemote {
	@WebMethod(operationName="sendMessage")
	void createMessage(Message message);
	@WebMethod(operationName="findByid")
	Message findMessage(@WebParam(name="id")int id);
	void updateMessage(Message message);
	void removeChallenge(Message message);
	List<Message> findAllMessages();
	@WebMethod(operationName="inbox")
	List<Message> findAllInBoxMessages(User u);
	@WebMethod(operationName="sendBox")
	List<Message> findAllSendBoxMessages(User u);

}
