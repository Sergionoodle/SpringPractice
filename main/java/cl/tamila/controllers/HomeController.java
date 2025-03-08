package cl.tamila.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")//Con esto todas las rutas heredan de "/" la raiz
public class HomeController {
	
	//Añadimos nuestro valor
	@Value("${sergio.valores.facto}")
	private String sergioValoresFacto;
	
	
	@GetMapping("/")
	//Le quitamos el response body para hacer la primera plantilla
	//@ResponseBody
	public String home()
	{
		//Para encontrar la plantilla 
		//home/carpeta -> home/archivo
		return "home/home";
	}
	
	//Ahora cuando se añada esto a la url se ira a una nueva pagina
	@GetMapping("/nosotros")
	@ResponseBody
	public String nosotros()
	{
		return "Hola desde nosotros";
	}

	@GetMapping("/parametros/{id}/{slug}")
	@ResponseBody			//Con esto podremos añadir un nuevo parametro 
	public String parametros( @PathVariable("id") Long id, @PathVariable("slug") String slug)
	{
		return "El id que se ha introducido es = " + id + "| slug = "+slug;
	}
	
	//QueryString &?=...
	@GetMapping("/query-string")
	@ResponseBody
	public String query_string( @RequestParam("id") Long id, @RequestParam("slug") String slug)
	{
		return "id = "+ id + " | slug = "+slug;
	}
	
	//Ahora vamos a printar el valor del application.properties
	@GetMapping("/valores")
	@ResponseBody
	public String valores()
	{
		return "los valores = "+sergioValoresFacto;
	}
	
	
	
}
