package cl.tamila.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.tamila.modelos.ProductRestModel;
import cl.tamila.services.ClienteRestService;

@Controller
@RequestMapping("/client-rest")
public class ClienteRestController {

	@Autowired
	private ClienteRestService clienteService;
	
	@GetMapping("")
	public String home(Model model) {
		List<ProductRestModel> datos = this.clienteService.listar();
		model.addAttribute("datos", datos);
		return "cliente_rest/home";
	}
}
