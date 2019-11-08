package com.ajude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AjudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjudeApplication.class, args);
	}
	/*
	@SuppressWarnings({"rawtypes","unchecked"})
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<Filtro> filterJwt() {
		FilterRegistrationBean<Filtro> filterRB = new FilterRegistrationBean<Filtro>();
		filterRB.setFilter(new Filtro());
		// Rotas que precisam de autenticação devem vir aqui
		// Exemplo: filterRB.addUrlPatterns("/auth/usuarios", "/disciplinas/likes/*", "/disciplinas/comentarios/*");
		//filterRB.addUrlPatterns();
		return filterRB;
	}
	*/
}
