package com.kh.semi.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	
		return http.formLogin(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)	
				.cors(Customizer.withDefaults()).authorizeRequests(requests ->{
					requests.requestMatchers(HttpMethod.POST, "/api/boards").permitAll();
					requests.requestMatchers(HttpMethod.PATCH, "/api/boards/**").permitAll();
					requests.requestMatchers(HttpMethod.DELETE, "/api/boards/**").permitAll();
					requests.requestMatchers(HttpMethod.GET, "/api/boards").permitAll();
					requests.requestMatchers(HttpMethod.GET, "/api/boards/**").permitAll();
					
				}).sessionManagement(manager ->
				manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
		}

	
	@Bean
	public AuthenticationManager authenticationManger(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	
	
	
	
	

}
