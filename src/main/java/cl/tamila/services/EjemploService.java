package cl.tamila.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//Para hacer una inyeccion de dependencias tenemos que crear "services"
//es lo mas normal, para ello ponemos las siguientes anotaciones y dentro
//irá cualquier tipo de metodo
@Service
@Primary //Para si tienes el mismo servicio dos veces que no se confunda
public class EjemploService {

	//Creamos un controlador
	public String saludo() {
		return "hola desde un service inyectado desde Spring";
	}
}
