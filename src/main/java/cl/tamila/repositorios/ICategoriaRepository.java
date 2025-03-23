package cl.tamila.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tamila.modelos.CategoriaModel;

//Extendemos del jpa repository							que modelo queremos y que tipo de ID tenemos
@Repository
public interface ICategoriaRepository extends JpaRepository<CategoriaModel, Integer>{
//Crear interfaz y servicio, en el servicio ejecutaremos las consultas mediante la interfaz
	
	//Nos buscara en caso de que el slug exista o no
	public boolean existsBySlug(String slug);
}
