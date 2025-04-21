package cl.tamila.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
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

	public boolean buscarPorSlug(String slug) {
		if (this.repository.existsBySlug(slug)) {
			return false;
		} else {
			return true;
		}
	}

	public ProductosModel editar(Integer id) {
		Optional<ProductosModel> optional = this.repository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	// Metodo para eliminar, de ejecucion (void)
	public void eliminarRegistro(Integer id) {
		this.repository.deleteById(id);
	}

	// Listado de productos con un orden, con esto se listaría segun su id
	public List<ProductosModel> listarPorNombre() {
		return this.repository.findAll(Sort.by("nombre").descending());

	}

	// Listamos por categorias
	public List<ProductosModel> listar_por_categorias(CategoriaModel categoria) {
		return this.repository.findAllByCategoriaId(categoria);
	}
}
