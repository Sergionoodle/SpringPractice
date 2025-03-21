package cl.tamila.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import modelos.CategoriaModel;
import repositorios.ICategoriaRepository;

@Service
@Primary
public class CategoriaService {
	

	private ICategoriaRepository repository;
	
	//Ahora creamos metodos para atacar al repository
	public List<CategoriaModel> listar() {
		//Con findAll traemos todo lo de la tabla categoria
		return this.repository.findAll();
	}

}
