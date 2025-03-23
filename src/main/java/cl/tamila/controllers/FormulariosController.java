package cl.tamila.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.tamila.modelos.InteresesModel;
import cl.tamila.modelos.PaisModel;
import cl.tamila.modelos.Usuario2Model;
import cl.tamila.modelos.Usuario3Model;
import cl.tamila.modelos.UsuarioCheckboxModel;
import cl.tamila.modelos.UsuarioModel;
import cl.tamila.modelos.UsuarioUploadModel;
import cl.tamila.utils.Utils;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/formularios")
public class FormulariosController {

	@GetMapping("")
	public String home() {
		return "formularios/home";
	}

	// -----FORMULARIO SIMPLE-----
	@GetMapping("/simple")
	public String simple() {
		return "formularios/simple";
	}

	// Metodo post maping para recivir la info
	@PostMapping("/simple")
	@ResponseBody
	public String simple_post(@RequestParam(name = "user") String username, @RequestParam(name = "email") String correo,
			@RequestParam(name = "pass") String pass) {
		return "usuario = " + username + "<br>email= " + correo + "<br>password= " + pass;
	}

	// -----FORMULARIO OBJETOS------
	@GetMapping("/objeto")
	public String objeto() {
		return "formularios/objeto";
	}

	@PostMapping("/objeto")
	@ResponseBody // Añadimos el modelo que queremos usar
	public String objeto_post(UsuarioModel model) {

		// Antes de empezar hay que ir a main y crear un modelo nuevo
		return "usuario = " + model.getUser() + "<br>email= " + model.getEmail() + "<br>password= " + model.getPass();

		// Tener en cuenta que ahora es un obj. se pueden usar get and set

	}

	// -----FORMULARIO OBJETOS 2------

	@GetMapping("/objeto2")
	public String objeto2(Model model) {
		model.addAttribute("user", new UsuarioModel());
		return "formularios/objeto2";
	}

// Añadimos el modelo que queremos usar
	@PostMapping("/objeto2")
	@ResponseBody
	public String objeto_post2(UsuarioModel model) {
		return "usuario = " + model.getUser() + "<br>email= " + model.getEmail() + "<br>password= " + model.getPass();
	}

	// -----VALIDACIONES------
	@GetMapping("/validaciones")
	public String validaciones(Model model) {
		model.addAttribute("user", new Usuario2Model());
		return "formularios/validaciones";
	}

	@PostMapping("/validaciones")
	public String validaciones_post(@Valid Usuario2Model usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
			});
			model.addAttribute("errores", errores);
			model.addAttribute("user", usuario);
			return "formularios/validaciones";
		}
		model.addAttribute("user", usuario);
		return "formularios/validaciones_result";
	}

	// FORMULARIO SELECT DINAMICO
	@GetMapping("/select_dinamico")
	public String select_dinamico(Model model) {

		model.addAttribute("user", new Usuario3Model());
		return "formularios/select_dinamico";
	}

	// Recordar que para validar tenemos que crear el siguiente
	// metodo
	@PostMapping("/select_dinamico")
	public String select_dinamico_post(@Valid Usuario3Model usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
			});
			model.addAttribute("errores", errores);
			model.addAttribute("user", usuario);
			return "formularios/select_dinamico";
		}
		model.addAttribute("user", usuario);
		return "formularios/select_result";
	}

	// FORMULARIO CHECKBOX
	@GetMapping("/checkbox")
	public String checkbox(Model model) {

		model.addAttribute("user", new UsuarioCheckboxModel());
		return "formularios/checkbox";
	}

	@PostMapping("/checkbox")
	public String checkbox_resultado(@Valid InteresesModel usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
			});
			model.addAttribute("errores", errores);
			model.addAttribute("user", usuario);
			return "formularios/checkbox";
		}
		model.addAttribute("user", usuario);
		return "formularios/check_resultado";
	}

	// EJEMPLO DE MENSAJES FLASH
	@GetMapping("/flash")
	public String flash(Model model) {
		model.addAttribute("user", new UsuarioModel());
		return "formularios/flash";
	}

	@PostMapping("/flash") // Para generar los mensajes flash hay que importar el redirectattributes
	public String flash_post(UsuarioModel model, RedirectAttributes flash) {

		// Esto lo enviará a la siguiente url, con redirect
		// es una cookie que durará un segundo
		if (model.getUser().equals("vaginon")) {

			flash.addFlashAttribute("clase", "primary");
			flash.addFlashAttribute("mensaje", "Hola vagina sapiens");

		} else {

			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Mensaje flash danger");

		}

		return "redirect:/formularios/flash-respuesta";
	}

	@GetMapping("/flash-respuesta")
	public String flash_respuesta() {
		return "formularios/flash-respuesta";
	}

	// Formulario de upload archibos
	// para poder subir la foto tendrémos que crear un modelo para
	// subir las fotos
	@GetMapping("/upload")
	public String upload(Model model) {
		model.addAttribute("user", new UsuarioUploadModel()); // Asegúrate de usar la clase correcta
		return "formularios/upload";
	}

	
	
	
	// Despues de crear el modelo y el html y agregarle la etiqueta para recibir
	// imagenes, creamos
	// la peticion POST
	@PostMapping("/upload") // recibimos el model //necesitaremos el resultado //necesitaremos un modelo// //Luego un multipart y en mensaje
	public String upload_post(@Valid UsuarioUploadModel usuario, BindingResult result, Model model,@RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes flash) {
			if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
			});

			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			return "formularios/upload";
		}
		if(multiPart.isEmpty()) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Tiene que ser JPG | JPEG | PNG");
			return "redirect:/formularios/upload";
		}
		
	if(!multiPart.isEmpty()) {								//donde se guardará
			String nombreImagen = Utils.guardarArchivo(multiPart, "C:\\Users\\srrex\\Downloads\\proyecto\\imgs\\");
			if(nombreImagen == "no") {
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("mensaje", "Tiene que ser JPG | JPEG | PNG");
				return "redirect:/formularios/upload";				
			}
			if( nombreImagen != null) {
				usuario.setFoto(nombreImagen);
			}
			
		}
		model.addAttribute("usuario", usuario);
		return "formularios/upload_result";
	}

	// Si tienes que pasar los campos de la lista de paises
	// a varios metodos se haria de esta manera
	// ModelAttribute (Se puede usar para compartir mas cosas
	// como cadenas de texto...)
	@ModelAttribute
	public void setGenericos(Model model) {
		// Creamos una lista de paises que le
		// pasaremos a nuestro selector
		List<PaisModel> paises = new ArrayList<PaisModel>();
		paises.add(new PaisModel(1, "España"));
		paises.add(new PaisModel(2, "Andorra"));
		paises.add(new PaisModel(3, "Japon"));

		// pasamos los paises
		model.addAttribute("paises", paises);

		List<InteresesModel> intereses = new ArrayList<InteresesModel>();
		intereses.add(new InteresesModel(1, "Música"));
		intereses.add(new InteresesModel(2, "Deportes"));
		intereses.add(new InteresesModel(3, "Politica"));
		intereses.add(new InteresesModel(4, "Economias"));
		model.addAttribute("intereses", intereses);
		model.addAttribute("url_upload", "C:\\Users\\srrex\\Downloads\\proyecto\\imgs\\");
		

	}

}
