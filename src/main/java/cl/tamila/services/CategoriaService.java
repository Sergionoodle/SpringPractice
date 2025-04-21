package cl.tamila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cl.tamila.modelos.CategoriaModel;
import cl.tamila.repositorios.ICategoriaRepository;

@Service
@Primary
public class CategoriaService {
	
	@Autowired
	private ICategoriaRepository repository;
	
	//Ahora creamos metodos para atacar al repository
	public List<CategoriaModel> listar() {
		//Con findAll traemos todo lo de la tabla categoria
		return this.repository.findAll();
	}
	
	//Metodo para guardar una nueva categoria
	public void guardar(CategoriaModel category) {
		this.repository.save(category);
	}
	
	public boolean buscarPorSlug (String slug) {
		if(this.repository.existsBySlug(slug)) {
			return false;
		} else {
			return true;
		}
	}
	
	//Metodo para listar por id
	public CategoriaModel listarPorId(Integer id) {
		Optional<CategoriaModel> optional = this.repository.findById(id);
		
		//Si existe el registro
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}
	
	//Metodo para eliminar, de ejecucion (void)
	public void eliminarRegistro(Integer id) {
		this.repository.deleteById(id);
	}
	
	//
}
