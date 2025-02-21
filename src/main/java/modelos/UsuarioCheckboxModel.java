package modelos;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioCheckboxModel{

	
	@NotEmpty(message = "esta vacio")
	private String user;
	@NotEmpty(message = "no es valido")
	@Email(message = "Email no es valido")
	private String email;
	@NotEmpty(message = "no esta bien")
	private String pass;
	

	private List<InteresesModel> intereses;


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public List<InteresesModel> getIntereses() {
		return intereses;
	}


	public void setIntereses(List<InteresesModel> intereses) {
		this.intereses = intereses;
	}

	
}
