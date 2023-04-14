package com.pigihi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pigihi.filter.AuthFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private AuthFilter authFilter;
	
	private static final String[] WHITE_LIST_URLS = {
//			"/user/shop/**"
	};
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterAt(authFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.cors()
			.and()
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers(WHITE_LIST_URLS)
			.permitAll()
			.requestMatchers(HttpMethod.POST, "/user/customer/self").hasAuthority("MS")
			.requestMatchers("/user/customer/self/**").hasAuthority("CUSTOMER")
			.requestMatchers("/user/customer/admin/**").hasAuthority("MS")
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		return http.build();
	}

}
