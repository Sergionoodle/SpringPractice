package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelos.ProductosModel;

@Repository
public interface IProductosRepository extends JpaRepository<ProductosModel, Integer>{

}
