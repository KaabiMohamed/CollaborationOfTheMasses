package managedBean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistence.Administrator;
import persistence.User;
import services.AdministratorDAOImplRemote;
import services.UserDAOImplRemote;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

@ManagedBean(name = "auth")
@SessionScoped
public class Authentification {
// commit mohamed
	User user = new User();
	Administrator admin = new Administrator();
	@EJB
	UserDAOImplRemote U_remote;
	@EJB
	AdministratorDAOImplRemote A_remote;

	public Authentification() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	void write(String s) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(
					"C:\\Users\\M.M.S.I\\Desktop\\chalnnegeData.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter p = new PrintWriter(bw);
		p.println(s);
		p.close();
	}

	public void authentificate(ActionEvent event) throws IOException {

		user.setLogin(admin.getLogin());
		user.setPassWord(admin.getPassWord());
		write(user.toString());
		User userFound = U_remote.findUser(user.getLogin());
		System.out.println("user found" + userFound);
		Administrator adminFound = A_remote.findAdministrator(admin.getLogin());
		System.out.println("\n admin found " + adminFound);
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();

		if (userFound == null && adminFound == null) { // Not Found
			ec.redirect("http://localhost:2000/WebPrimeFaces/faces/erreur.html");
		}
		if (userFound == null
				&& adminFound.getPassWord().equals(admin.getPassWord())) { // admin
																			// Login
			ec.getSessionMap().put("admin", adminFound);
			ec.redirect("http://localhost:2000/WebPrimeFaces/faces/AdminMenu.xhtml");
			
		}
		if (adminFound == null
				&& userFound.getPassWord().equals(user.getPassWord())) { // user
																			// login
			ec.getSessionMap().put("user", userFound);
			ec.redirect("http://localhost:2000/WebPrimeFaces/faces/UserMenu.xhtml");
		}

	}

	public void facebookConnect(ActionEvent event) throws IOException {

		FacebookClient facebookClient = new DefaultFacebookClient();
		// token = facebookClient.obtainAppAccessToken(appId, appSecret);
		facebookClient = new DefaultFacebookClient(
				"AAABkMJxwRzYBACcKY9wosugWRVdmygLuZCnhBJZAmexZClzB684vDtnjZAiPZBcCMzY0cSskbPZCjfVZBH0ZCBOtgmW95fJEeBGFY7OxZCZATnHwZDZD");
		// AAABkMJxwRzYBACcKY9wosugWRVdmygLuZCnhBJZAmexZClzB684vDtnjZAiPZBcCMzY0cSskbPZCjfVZBH0ZCBOtgmW95fJEeBGFY7OxZCZATnHwZDZD
		com.restfb.types.User user = facebookClient.fetchObject("me",
				com.restfb.types.User.class);

		this.user = new User(user.getEmail(), "", user.getFirstName(),
				user.getLastName(), "", "", "Facebook", "Facebook", null, null,
				new Date());
		U_remote.createUser(this.user);
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		ec.redirect("http://localhost:2000/WebPrimeFaces/faces/UserMenu.xhtml");

	}
}
