package cl.tamila.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.tamila.services.EjemploService;

@Controller
@RequestMapping("/ejemplo-service")
public class EjemploServiceController {
	
	//Vamos a usar el servicio
	@Autowired
	private EjemploService ejemploService;
	
	//Primer metodo
	@GetMapping("") //ahora añadimos un model
	public String home(Model model)
	{
		//Añadimos el atributo y usamos el metodo del atributo privado
		//Que hemos creado
		model.addAttribute("saludo", this.ejemploService.saludo());
		return "ejemplo_service/home";
	}

}
