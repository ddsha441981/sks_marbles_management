package com.cwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

	                // Second mapping
//	                registry
//	                    .addMapping("/api/v1/category/**")
//	                    .allowedHeaders("Requestor-Type")
//	                    .exposedHeaders("X-Get-Header")
//	                    .maxAge(3600L)
//	                    .allowedOrigins("http://localhost:4200")
//	                    .allowedMethods("GET", "POST")
//	                    .maxAge(3600L)
//	                    .allowCredentials(false);
	                
	                // Add more mappings here if needed
	            }
	        };
	    }
}
