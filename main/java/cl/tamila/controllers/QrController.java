package cl.tamila.controllers;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.WriterException;

import cl.tamila.services.QrCodeService;

@Controller
@RequestMapping("/qr")
public class QrController {

	@Autowired
	private QrCodeService qrCode;

	@GetMapping("")
	public String qr() {
		return "qr/home";
	}

	@GetMapping("/generate")
	public String generate(Model model) {
		String url = "https://www.youtube.com/watch?v=v-rWnobdpTM&ab_channel=SanPitoPato";
		byte[] imagen = new byte[0];
		try {
			imagen = this.qrCode.crearQr(url, 250, 250);
		}catch ( WriterException | IOException e) {
			System.out.println("Sa rompido, mete un flash");
		}
		//convertir el byte en base 64 string
		String qrCodeBase = Base64.getEncoder().encodeToString(imagen);
		
		model.addAttribute("qrcodebase", qrCodeBase);
		model.addAttribute("url", url);

		return "qr/create";
	}
	
	
}
