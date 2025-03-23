package cl.tamila.componentes;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import cl.tamila.modelos.PaisModel;

//Esto nos ayuda a que no se nos lie el atributo que quiere ser un
//String
								//Nos convertira de String(lo que
										//se lia) y el segundo lo que
										//necesitamos
@Component									
public class ConvertirPaisId implements org.springframework.core.convert.converter.Converter<String, PaisModel>{

	@Override
	public PaisModel convert(String source) {
		Integer id = Integer.valueOf(source);
		PaisModel datos = new PaisModel();
		datos.setId(id);
		return datos;
	}

	

	
}
