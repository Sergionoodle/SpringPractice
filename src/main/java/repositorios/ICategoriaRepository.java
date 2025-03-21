package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.CategoriaModel;

//Extendemos del jpa repository							que modelo queremos y que tipo de ID tenemos
public interface ICategoriaRepository extends JpaRepository<CategoriaModel, Integer>{
//Crear interfaz y servicio, en el servicio ejecutaremos las consultas mediante la interfaz
	
	
}
