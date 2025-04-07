package cl.tamila.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tamila.modelos.ProductosModel;

@Repository
public interface IProductosRepository extends JpaRepository<ProductosModel, Integer>{

	public boolean existsBySlug(String slug);

}
