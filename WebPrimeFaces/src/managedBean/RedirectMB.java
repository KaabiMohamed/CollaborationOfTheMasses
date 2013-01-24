package managedBean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


@ManagedBean (name="redirect")
@SessionScoped
public class RedirectMB {
	
	public void testUser() throws IOException{
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user")==null)
		{System.out.println("NOooooooooooooooooOOOOooooo");
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:2000/WebPrimeFaces/faces/Authentification.xhtml");}
		else System.out.println("YeeeeeeeeeEEEEeeeeeeeeeees");
		}
	public void testAdmin() throws IOException{
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin")==null)
		{System.out.println("NOooooooooooooooooOOOOooooo");FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:2000/WebPrimeFaces/faces/Authentification.xhtml");}
		else System.out.println("YeeeeeeeeeEEEEeeeeeeeeeees");

		}
	public void destroySession(ActionEvent event) throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", null);
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:2000/WebPrimeFaces/faces/Authentification.xhtml");
	}
	public RedirectMB() {
		
	}
	
	

}
