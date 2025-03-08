package cl.tamila.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/template")
public class TemplateController {

	@GetMapping("")
	// Usamos Model para pasar objetos de nuestro modelo de spring
	public String home(Model model) {

		String nombre = "Sergio";
		// Ahora podemos añadir addAttribute
		model.addAttribute("nombre", nombre);
		return "template/home";
	}

	@GetMapping("/atributos")
	public String atributos(Model model) {

		//Creamos variables
		Integer num1 = 12;
		Integer num2 = 13;
		Integer cifra = 12345;
		Date fecha = new Date();
		List<String> paises = new ArrayList<String>();
		//Añadimos dentro de nuestro array list
		paises.add("ESPAÑA");
		paises.add("ANDORRA");
		paises.add("JAPON");
		//Con el modelo declarado le enviamos los atributos con un 
		//nombre y un valor 
		model.addAttribute("num1", num1);
		model.addAttribute("num2", num2);
		model.addAttribute("cifra", cifra);
		model.addAttribute("fecha", fecha);
		model.addAttribute("paises", paises);

		//Return de la carpeta/archivo html
		return "template/atributos";
	}
	
	
	@GetMapping("/estaticos")
	public String estaticos(Model model)
	{
		return "template/estaticos";
	}

	@GetMapping("/estaticos2")
	public String estaticos2(Model model)
	{
		return "template/estaticos2";
	}
	
	@GetMapping("/links")
	public String links(Model model)
	{
		return "template/links";
	}
	
	
	@GetMapping("/ajax")
	public String ajax(Model model)
	{
		return "template/ajax";
	}
	
	@GetMapping("/peticion")			//para añadir por la url una variable 
	public String peticion(Model model, @RequestParam("valor") String valor)
	{
		model.addAttribute("valor", valor);
		return "template/peticion";
	}
	
	@GetMapping("/fancybox")
	public String fancybox(Model model)
	{
		return "template/fancybox";
	}
	
	
}
