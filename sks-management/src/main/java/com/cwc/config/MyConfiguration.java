package com.cwc.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class MyConfiguration {

	@Bean
    public WebMvcConfigurer corsConfigurer() {
		 return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                // First mapping
	                registry
	                    .addMapping("/api/v1/**")
	                    .allowedHeaders("*")
	                    .allowedOrigins("http://localhost:4200")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE")
	                    .exposedHeaders("X-Get-Header")
	                    .maxAge(3600L)
	                    .allowCredentials(false);
	            }
	        };
	    }
	
	//Swagger Configuration
		@Bean
		public OpenAPI myOpenAPI() {
			Contact contact = new Contact();
			contact.setEmail("codwithcup.developer@gmail.com");
			contact.setName("Deendayal Kumawat");
			contact.setUrl("https://www.github.com/ddsha441981");

			Server localServer = new Server();
			localServer.setUrl("http://localhost:8089");
			localServer.setDescription("Server URL in Local environment");

			Server productionServer = new Server();
			productionServer.setUrl("https://www.github.com/ddsha441981");
			productionServer.setDescription("Server URL in Production environment");

			License mitLicense = new License()
					.name("MIT License")
					.url("https://choosealicense.com/licenses/mit/");

			Info info = new Info()
					.title("INVENTORY MANAGEMENT API")
					.contact(contact)
					.version("1.0")
					.description("This API exposes endpoints for to manage their products.")
					.termsOfService("https://my-awesome-api.com/terms")
					.license(mitLicense);

			return new OpenAPI()
					.info(info)
					.servers(List.of(localServer, productionServer));
		}
	
	
}
