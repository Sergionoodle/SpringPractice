package cl.tamila.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import modelos.ProductosModel;
import repositorios.IProductosRepository;

@Service
@Primary
public class PreciosService {

	
	private IProductosRepository repository;
	
	public List<ProductosModel> listar() {
		return this.repository.findAll();
	}
}
