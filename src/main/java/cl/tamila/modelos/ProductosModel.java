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
	
	@NotEmpty(message = "Validacion, esto esta vacío")
	private String nombre;
	
	@NotEmpty(message = "Validacion, esto esta vacío")
	private String slug;
	
	@NotEmpty(message = "Validacion, esto esta vacío")
	private String descripcion;
	
	@NotNull(message = "No puede ser nulo") //Usando @Min(x) o @Max(x) asignamos un minimo o maximo
	private Integer precio;
	
	@NotEmpty(message = "Validacion, esto esta vacío")
	private String foto;
	
	//relación one to one
	@OneToOne
	@JoinColumn(name = "categoria_id")//nom del campo en tu base de datos
	private CategoriaModel categoriaId; //Esto tiene que trabajar como una llave foranea
	
	//Ahora toca hacer las interfaces
}
