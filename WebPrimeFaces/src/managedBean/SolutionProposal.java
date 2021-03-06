package managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

import com.osgi.ejb.integration.EJB.SLSB.GD;

import persistence.Challenge;
import persistence.Solution;
import services.ChallengeDAOImplRemote;
import services.SolutionDAOImplRemote;

@ManagedBean(name = "SolutionPropsal")
@SessionScoped
public class SolutionProposal {

	@EJB
	SolutionDAOImplRemote Rsolution;
	@EJB
	ChallengeDAOImplRemote Rchallenge;
	@EJB
	GD gd;
	
	private Solution solution = new Solution();
	private List<Challenge> challenges;
	private List<Challenge> filteredCh;
	private Challenge selectedRow;

	public List<Challenge> getChallenges() {
		challenges = Rchallenge.findChallengeByValid(true);
		return challenges;
	}

	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}

	public List<Challenge> getFilteredCh() {
		return filteredCh;
	}

	public void setFilteredCh(List<Challenge> filteredCh) {
		this.filteredCh = filteredCh;
	}

	public Challenge getSelectedRow() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		ec.getSessionMap().put("selectedCh", selectedRow);
		return selectedRow;
	}

	public void setSelectedRow(Challenge selectedRow) {
		this.selectedRow = selectedRow;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	String descriptionFile;

	public String getDescriptionFile() {
		return descriptionFile;
	}

	public void setDescriptionFile(String descriptionFile) {
		this.descriptionFile = descriptionFile;
	}

	public String url;

	public String getUrl() {
		url = gd.getVerificationUrl();
		return url;
	}

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String destination = "D:\\upload\\";

	public String login() {

		try {
			gd.login(code);
			FacesMessage msg = new FacesMessage("Success! ",
					"Connexion etablie");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public void upload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName()
				+ " is uploaded in "
				+ destination
				+ event.getFile().getFileName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		session.setAttribute("id", destination + event.getFile().getFileName());

		try {

			copyFile(event.getFile().getFileName(), event.getFile()
					.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void copyFile(String fileName, InputStream in) {
		try {

			File f = new File(destination + fileName);
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(f);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String uplodInGoogleDrive(ActionEvent event) throws IOException {
		

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);

		try {

			File f1 = new File("" + session.getAttribute("id"));
			System.out.println("" + session.getAttribute("id"));
			gd.uplaodFile(f1, fileName, descriptionFile, "ttt");

			FacesMessage msg1 = new FacesMessage("Success! ",
					"Upload in Google drive ");
			FacesContext.getCurrentInstance().addMessage(null, msg1);
			//Add Solution
			ExternalContext context1 = FacesContext.getCurrentInstance()
					.getExternalContext();
			Challenge currentCh = (Challenge) context1.getSessionMap().get(
					"selectedCh");
			System.out.println(currentCh);
			Solution s = new Solution();
			s.setChallenge(currentCh);
			s.setDate(new Date());
			s.setDescription(descriptionFile);
			s.setFileName(fileName);
			s.setSolver(null);
			s.setAwarded(false);
			System.out.println(s);
			Rsolution.createSolution(s);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}


	public SolutionProposal() {
		// TODO Auto-generated constructor stub
	}

}
