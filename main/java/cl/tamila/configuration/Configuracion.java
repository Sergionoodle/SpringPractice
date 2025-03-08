package cl.tamila.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration						//Extendemos una interfaz para el proyecto
public class Configuracion implements WebMvcConfigurer{

	//Le diremos hacia donde van los recursos estaticos (imagenes)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/uploads/**")
		.addResourceLocations("file: C:\\Users\\srrex\\Downloads\\proyecto\\imgs");
	}

	
}
