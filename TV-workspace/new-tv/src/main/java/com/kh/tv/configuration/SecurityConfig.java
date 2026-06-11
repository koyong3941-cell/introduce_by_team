package com.kh.tv.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

import com.kh.tv.configuration.filter.JwtFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	//private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("저주받음");
		return http.formLogin(AbstractHttpConfigurer::disable)
				   .httpBasic(AbstractHttpConfigurer::disable)
				   .csrf(AbstractHttpConfigurer::disable)
//				   .cors(AbstractHttpConfigurer::disable)
				   .cors(Customizer.withDefaults())
				   .authorizeHttpRequests(requests -> {
					   // POST방식으로 /members라는 요청이 오면 권한체크 안하고 전부 허용
					   requests.requestMatchers("/api/members").permitAll();
					   requests.requestMatchers("/test").permitAll();
				   }).sessionManagement(manager ->
				   						manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				   .build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		System.out.println("머지?");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:5174", "http://localhost"));
		configuration.setAllowedMethods(Arrays.asList("POST", "PATCH", "DELETE", "GET", "PUT", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
}
