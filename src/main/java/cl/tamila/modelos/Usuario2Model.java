package cl.tamila.modelos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class Usuario2Model {

	// Añadimos los campos que vamos a usar
	//Añadimos la primera validacion con las etiquetas (se tiene que añadir
	//un nuevo recurso al pom
	@NotEmpty(message = "esta vacio")
	private String user;
	@NotEmpty(message = "no es valido")
	@Email(message = "Email no es valido")
	private String email;
	@NotEmpty(message = "no esta bien")
	private String pass;
	
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
