package com.example.TiendaAbarrotes.Configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@org.springframework.context.annotation.Configuration
public class Configuracion {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((requests) -> requests
	            .requestMatchers("/productos/listar").authenticated() // Requiere autenticación para listar
	            .requestMatchers("/productos/agregar").authenticated() // Requiere autenticación para agregar
	            .requestMatchers("/productos/agregarProducto").authenticated() 
	            .requestMatchers("/productos/**").permitAll() // Permite acceso a otras rutas bajo /productos sin autenticación
	            .anyRequest().permitAll() // Permite acceso a todas las demás rutas sin autenticación
	        )
	        .formLogin((form) -> form
	            .loginPage("/productos/login") // Página de login personalizada
	            .defaultSuccessUrl("/productos/listar", true) // Redirigir a /productos/listar después del login
	            .permitAll()
	        )
	        .logout((logout) -> logout
	            .logoutRequestMatcher(new AntPathRequestMatcher("/productos/logout"))
	            .permitAll()
	        )
	        .csrf().disable(); // Deshabilitar CSRF para pruebas; no recomendado para producción

	    return http.build();
	}



    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
