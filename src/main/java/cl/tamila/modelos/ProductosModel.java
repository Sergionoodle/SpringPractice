package cl.tamila.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class ProductosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//relación one to one
	@OneToOne
	@JoinColumn(name = "categoria_id")//nom del campo en tu base de datos
	private CategoriaModel categoriaId; //Esto tiene que trabajar como una llave foranea
	
	
	@NotEmpty(message = "Validacion, esto esta vacío")
	private String nombre;
	
	private String slug;
	
	@NotEmpty(message = "Validacion, esto esta vacío")
	private String descripcion;
	
	@NotNull(message = "No puede ser nulo") //Usando @Min(x) o @Max(x) asignamos un minimo o maximo
	private Integer precio;
	
	@NotEmpty(message = "Validacion, esto esta vacío")
	private String foto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CategoriaModel getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(CategoriaModel categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	

	//Ahora toca hacer las interfaces
	
}
