package com.example.workaholic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}
	
//	@Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of("http://127.0.0.1:5000")); // Allow requests from all domains. You can restrict it to specific domains.
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods
//        corsConfiguration.setAllowedHeaders(List.of("*")); // Allowed request headers
//        corsConfiguration.setAllowCredentials(true); // Allow sending credentials like cookies
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration); // Apply the CORS configuration to all paths
//
//        return new CorsFilter(source);
//    }
	
	// Bean to configure multipart resolver
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSizePerFile(10 * 1024 * 1024); // Set maximum file size (e.g., 10MB)
//        return resolver;
//    }

}
