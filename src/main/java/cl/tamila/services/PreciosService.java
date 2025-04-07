package cl.tamila.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cl.tamila.modelos.CategoriaModel;
import cl.tamila.modelos.ProductosModel;
import cl.tamila.repositorios.IProductosRepository;

@Service
@Primary
public class PreciosService {

	@Autowired
	private IProductosRepository repository;
	
	public List<ProductosModel> listar() {
		return this.repository.findAll();
	}
	
	public void guardar(ProductosModel productos) {
		this.repository.save(productos);
	}
	
	public boolean buscarPorSlug (String slug) {
		if(this.repository.existsBySlug(slug)) {
			return false;
		} else {
			return true;
		}
	}
}
