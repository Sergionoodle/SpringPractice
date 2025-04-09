package cl.tamila.controllers;

import javax.swing.text.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.tamila.modelos.CategoriaModel;
import cl.tamila.modelos.ProductosModel;
import cl.tamila.services.CategoriaService;
import cl.tamila.services.PreciosService;
import cl.tamila.utils.Utils;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/jpa")
public class JPAController {

	// Las inyecciones van por aqui mas ordenado
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private PreciosService productService;

	@GetMapping("")
	public String home(Model model) {
		return "jpa/home";
	}

	// Vamos a listar las categorias
	@GetMapping("/categorias")
	public String categorias(Model model) {
		model.addAttribute("datos", this.categoriaService.listar());
		return "jpa/categorias";
	}

	// Creamos el metodo de añadir categorias
	@GetMapping("/categorias/add")
	public String categorias_add(Model model) {
		model.addAttribute("categoria", new CategoriaModel());
		return "jpa/categorias_Add";
	}

	@PostMapping("/categorias/add")
	public String add_post(@Valid @ModelAttribute("categoria") CategoriaModel category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "jpa/categorias_Add";
		}

		category.setSlug(Utils.getSlug(category.getNombre()));

		if (!categoriaService.buscarPorSlug(category.getSlug())) {
			model.addAttribute("errorSlug", "El slug ya existe, prueba con otro nombre.");
			return "jpa/categorias";
		}

		categoriaService.guardar(category);
		return "redirect:/jpa/categorias";
	}

	// Creamos el metodo para editar las categorias
	@GetMapping("/categorias/editar/{id}")
	public String categorias_edit(@PathVariable("id") Integer id, Model model) {
		CategoriaModel categoria = this.categoriaService.listarPorId(id);
		model.addAttribute("categorias", categoria);

		return "jpa/categorias_edit";
	}

	@PostMapping("/categorias/editar/{id}")
	public String categorias_edit_post(@Valid @ModelAttribute("categorias") CategoriaModel categoria,
			BindingResult result, @PathVariable("id") int id, RedirectAttributes flash, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("categorias", categoria);
			return "jpa/categorias_edit";
		}

		// Verificamos si la categoría existe antes de actualizarla
		CategoriaModel existente = categoriaService.listarPorId(id);
		if (existente == null) {
			flash.addFlashAttribute("error", "La categoría no existe.");
			return "redirect:/jpa/categorias";
		}

		// Actualizamos los valores de la categoría existente
		existente.setNombre(categoria.getNombre());
		existente.setSlug(Utils.getSlug(categoria.getNombre()));

		// Guardamos los cambios
		categoriaService.guardar(existente);

		flash.addFlashAttribute("success", "Categoría actualizada correctamente.");
		return "redirect:/jpa/categorias";
	}

	@GetMapping("/categorias/delete/{id}")
	public String categorias_delete(@PathVariable("id") Integer id, Model model) {
		try {
			this.categoriaService.eliminarRegistro(id);
			return "redirect:/jpa/categorias";
		} catch (Exception e) {
			System.out.println("HA FALLADO!!!");
			return "redirect:/jpa/categorias"; 
		}
	}
	
	// Vamos a listar las categorias
	@GetMapping("/productos")
	public String productos(Model model) {
		model.addAttribute("datos", this.productService.listar());
		return "jpa/productos";
	}
	

	// Creamos el metodo de añadir categorias
	@GetMapping("/productos/add")
	public String productos_add(Model model) {
		model.addAttribute("productos", new ProductosModel());
		model.addAttribute("categorias", this.categoriaService.listar());
		return "jpa/productos_add";
	}
	
	@PostMapping("/productos/add")
	public String add_productos_post(@Valid @ModelAttribute("productos") ProductosModel productos,
	                                 BindingResult result, Model model) {

	    if (productos.getPrecio() < 0) {
	        result.rejectValue("precio", "error.precio", "El precio no puede ser negativo.");
	    }

	    if (productos.getCategoriaId() == null || productos.getCategoriaId().getId() == null) {
	        result.rejectValue("categoriaId", "error.categoriaId", "Debe seleccionar una categoría.");
	    }

	    productos.setSlug(Utils.getSlug(productos.getNombre()));

	    if (!productService.buscarPorSlug(productos.getSlug())) {
	        model.addAttribute("errorSlug", "El nombre del producto ya existe.");
	    }

	    if (result.hasErrors() || model.containsAttribute("errorSlug")) {
	    	model.addAttribute("categorias", this.categoriaService.listar());
	        return "jpa/productos_add";
	    }

	    productService.guardar(productos);
	    return "redirect:/jpa/productos";
	}


}
