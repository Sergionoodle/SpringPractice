package cl.tamila.modelos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class Usuario3Model {

	@NotEmpty(message = "esta vacio")
	private String user;
	@NotEmpty(message = "no es valido")
	@Email(message = "Email no es valido")
	private String email;
	@NotEmpty(message = "no esta bien")
	private String pass;
	
	//Despues de crear el nuevo modelo de paises 
	//creamos el nuevo campo con sus getters y setters
	private PaisModel paisId;
	
	public PaisModel getPaisId() {
		return paisId;
	}
	public void setPaisId(PaisModel paisId) {
		this.paisId = paisId;
	}
	//Creamos los getters y setters
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

	
}
