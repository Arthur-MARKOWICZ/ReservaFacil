package com.reservafacil.reservafacil.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /****
     * Configures CORS (Cross-Origin Resource Sharing) settings for the application.
     *
     * Allows requests from specific origins with GET, POST, PUT, and DELETE methods, permits all headers, supports credentials, and sets the preflight cache duration to 3600 seconds for all URL paths.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500","http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);

    }
}