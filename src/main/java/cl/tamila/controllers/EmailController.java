package cl.tamila.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.tamila.services.EmailServices;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@Controller
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailServices emailService;
	//Para enviar emails, lo primero que tenemos que hacer es crearons
	//Un nuevo servicio
	
	@GetMapping("")
	public String email(Model model) {
		
		
		return "email/home";
	}
	
	@GetMapping("/send")
	public String send(Model model) throws AddressException, MessagingException {
		
		//Ahora hacemos el envio
		this.emailService.sendMail("sergiotest@yopmail.com", "msg");
		return " ";
	}
	

}
