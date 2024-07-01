//package com.airport_database.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Questo permette CORS su tutte le rotte
//                .allowedOrigins("*") // Questo permette tutte le origini
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metodi HTTP permessi
//                .allowedHeaders("*") // Permette tutti gli header
//                .allowCredentials(true); // Permette credenziali come cookies, autorizzazione headers, etc.
//    }
//}
