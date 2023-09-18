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
                registry.addMapping("/api/v1/**") // Define the URL patterns to apply CORS to
                        .allowedOrigins("http://localhost:4200") // Replace with the actual URL of your Angular app
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true); // If you need to send cookies or authentication headers
            }
        };
    }
}
