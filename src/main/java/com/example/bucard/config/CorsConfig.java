package com.example.bucard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/user/**")
            .allowedOrigins("*") // Replace with your domain
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add other methods as needed
            .allowedHeaders("*")
//            .allowCredentials(true)
            .maxAge(3600); // Max age of preflight request
    }
}
