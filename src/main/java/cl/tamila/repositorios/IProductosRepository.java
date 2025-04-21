package cl.tamila.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tamila.modelos.CategoriaModel;
import cl.tamila.modelos.ProductosModel;

@Repository
public interface IProductosRepository extends JpaRepository<ProductosModel, Integer>{

	//Para una consulta de caracter compuesto
	List<ProductosModel> findAllByCategoriaId(CategoriaModel categoria);
	
	public boolean existsBySlug(String slug);

	
}
