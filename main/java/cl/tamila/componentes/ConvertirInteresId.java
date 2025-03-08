package cl.tamila.componentes;

import modelos.InteresesModel;

public class ConvertirInteresId implements org.springframework.core.convert.converter.Converter<String, InteresesModel>{

	@Override
	public InteresesModel convert(String source) {
		Integer id = Integer.valueOf(source);
		InteresesModel datos = new InteresesModel();
		datos.setId(id);
		return datos;
	}

}
