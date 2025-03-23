package cl.tamila.utils;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.text.Utilities;

import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.pattern.Util;

//Para poder hacer cualquier upload de forma reutilizable
public class Utils {

	public static String guardarArchivo(MultipartFile multiPart, String ruta) {

		// Si es una mimeType esperado
		if (Utils.validImages(multiPart.getContentType())) {

			// vamos a dar el nombre para el servidor y guardar sin problemas
			long time = System.currentTimeMillis();

			String nombre = time + Utils.getExtension(multiPart.getContentType());

			try {

				File imageFile = new File(ruta + nombre);
				multiPart.transferTo(imageFile);
				return nombre;

			} catch (IOException e) {
				return null;
			}
		} else {
			return "NO";
		}
	}

	// mime verificará que esta
	// subiendo, evitar ataques!!
	public static boolean validImages(String mime) {
		boolean retorno = false;
		// Verificamos la extension
		switch (mime) {
		case "image/jpeg":
			retorno = true;
			break;
		case "image/jpg":
			retorno = true;
			break;
		case "image/png":
			retorno = true;
			break;
		default:
			retorno = false;
			break;
		}
		return retorno;
	}

	public static String getExtension(String mime) {
		String retorno = "";
		// Verificamos la extension
		switch (mime) {
		case "image/jpeg":
			retorno = ".jpeg";
			break;
		case "image/jpg":
			retorno = ".jpg";
			break;
		case "image/png":
			retorno = ".png";
			break;
		}
		return retorno;
	}
	
	
	// slug
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

	public static String getSlug(String input) {
	    String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
	    String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
	    String slug = NONLATIN.matcher(normalized).replaceAll("");
	    slug = EDGESDHASHES.matcher(slug).replaceAll("");
	    return slug.toLowerCase(Locale.ENGLISH);
	}
	
}
