package modelos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioUploadModel {
	// Añadimos los campos que vamos a usar
	@NotEmpty(message = "está vacio")
	private String user;
	@NotEmpty(message = "está vacio")
	@Email(message = "ingreso no vaildo")
	private String email;
	@NotEmpty(message = "está vacío")
	private String pass;
	
	private String foto = "default.png";
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
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
