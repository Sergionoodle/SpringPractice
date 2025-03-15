package cl.tamila.services;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import modelos.ProductRestModel;

@Service
@Primary
public class ClienteRestService {
	
	@Autowired
	private RestTemplate clientRest;
	
	@Value("sergio.valor.api")
	private String apiHost;
	
	public ClienteRestService(RestTemplateBuilder builder) {
		this.clientRest = builder.build();
	}
	
	private HttpHeaders setHeaders() 
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer ");
		return headers;
	}
	
	public List<ProductRestModel> listar() {
		HttpEntity<String> entity = new HttpEntity<>(this.setHeaders());
		ResponseEntity<List<ProductRestModel>> response = this.clientRest.exchange(apiHost+"productos", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProductRestModel>>() {
		});
		return response.getBody();
	}
}
