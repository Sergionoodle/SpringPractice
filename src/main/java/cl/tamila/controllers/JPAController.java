package cl.tamila.controllers;

import javax.swing.text.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.tamila.modelos.CategoriaModel;
import cl.tamila.services.CategoriaService;
import cl.tamila.utils.Utils;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/jpa")
public class JPAController {

	// Las inyecciones van por aqui mas ordenado
	@Autowired
	private CategoriaService categoriaService;

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

	@GetMapping("/categorias/delete")
	public String categorias_delete(Model model) {
		model.addAttribute("datos", this.categoriaService.listar());
		return "jpa/categorias_Delete";
	}

	@GetMapping("/categorias/edit")
	public String categorias_edit(Model model) {
		model.addAttribute("datos", this.categoriaService.listar());
		return "jpa/categorias_Edit";
	}
}
