package cl.tamila.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Utilities;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
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
	//Ahora como lo usamos para ordenar, le pasamos un request param que nos cambiara la 
	//lista en caso de darle a un boton u otro
	public String productos(@RequestParam(name = "ordenado", required = false, defaultValue = "false") boolean ordenado, Model model) {
		if (ordenado) {
			model.addAttribute("datos", this.productService.listarPorNombre());
		} else {
			model.addAttribute("datos", this.productService.listar());
		}
		return "jpa/productos";
		
	}
	
	//Vamos a sacar solo los productos de la categoria que queramos
	@GetMapping("/productos_where")
	public String productos_where(Model model) {
		//Creamos la lista de categorias que le queremos pasar
		List<CategoriaModel> lista = new ArrayList<CategoriaModel>();
		//Hacemos que listemos solo las categorias 3 y 4
		lista.add(this.categoriaService.listarPorId(2));
		lista.add(this.categoriaService.listarPorId(4));
		//Llamamos al listIn y le pasamos la lista de categorias 
		model.addAttribute("datos", this.productService.listIn(lista));
		return "jpa/productos_where";
	}
	
	//Productos con paginacion
	@GetMapping("/productos_paginacion")
	//Vamos a hacer una paginacion que se pase cada dos registros
	//primero vamos al servicio
	public String productos_paginacion(@RequestParam(value = "page", required = false) Integer page,  Model model) {
		//Variable de la paginacion
		Integer pagina = 2;
		
		//Ordenará los registros si quieres
		Pageable pageable = PageRequest.of((page == null)? 0 : page, pagina,
				Sort.by("id").ascending());
		
		//Una vez tenemos el numero de paginas que queremos agragar (ternario para evitar que cuando pasemos null, nos reviente)
		//ahora le pasamos el objeto pageable a nuestra lista paginada, ahora toca hacer los botones para las paginas
		model.addAttribute("datos", this.productService.listarPaginado(pageable));
		model.addAttribute("paginacion", "jpa/productos_paginacion");
		return "jpa/productos_paginacion";
	}
	
	//Ahora vamos a crear el metodo que nos haga la busqueda
	@GetMapping("/productos/categorias/{id}")
	public String productos_categoria(@PathVariable("id") Integer id, Model model) {
		CategoriaModel categoria = this.categoriaService.listarPorId(id);
		
		//Evitar algun valor raro
		if (categoria == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Página no encontrada");
		}
		
		model.addAttribute("datos", this.productService.listar_por_categorias(categoria));
		model.addAttribute("categoria", categoria);
		return "jpa/productos_categorias";
	}


	// Creamos el metodo de añadir categorias
	@GetMapping("/productos/add")
	public String productos_add(Model model) {
		model.addAttribute("productos", new ProductosModel());
		model.addAttribute("categorias", this.categoriaService.listar());
		return "jpa/productos_add";
	}

	@PostMapping("/productos/add")
	public String add_productos_post(@Valid @ModelAttribute("productos") ProductosModel productos, BindingResult result,
			Model model) {

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

	@GetMapping("/productos/editar/{id}")
	public String productos_edit(@PathVariable("id") Integer id, Model model) {
		ProductosModel productos = this.productService.editar(id);
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", this.categoriaService.listar());
	
		return "jpa/productos_edit";
	}

	@PostMapping("/productos/editar/{id}")
	public String productos_edit_post(@Valid @ModelAttribute("productos") ProductosModel productos,
			BindingResult result, @PathVariable("id") int id, RedirectAttributes flash, Model model) {
		System.out.println("HOLA");
		if (result.hasErrors()) {
			model.addAttribute("productos", productos);
			return "jpa/productos_edit";
		}

		if (productos.getPrecio() < 0) {
			result.rejectValue("precio", "error.precio", "El precio no puede ser negativo.");
		}
		
		// Verificamos si la categoría existe antes de actualizarla
		ProductosModel existente = this.productService.editar(id);
		if (existente == null) {
			flash.addFlashAttribute("error", "El producto no ha sido encontrado");
			return "redirect:/jpa/categorias";
		}

		// Actualizamos los valores de la categoría existente
		existente.setNombre(productos.getNombre());
		existente.setSlug(Utils.getSlug(productos.getNombre()));
		existente.setCategoriaId(productos.getCategoriaId());
		existente.setDescripcion(productos.getDescripcion());
		existente.setPrecio(productos.getPrecio());
		existente.setFoto(productos.getFoto());
		
		
		// Guardamos los cambios
		productService.guardar(existente);

		flash.addFlashAttribute("success", "Categoría actualizada correctamente.");
		return "redirect:/jpa/productos";
	}
	
	@GetMapping("/productos/delete/{id}")
	public String productos_delete(@PathVariable("id") Integer id, Model model) {
		try {
			this.productService.eliminarRegistro(id);
			return "redirect:/jpa/productos";
		} catch (Exception e) {
			System.out.println("HA FALLADO!!!");
			return "redirect:/jpa/productos";
		}
	}

}
