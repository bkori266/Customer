package com.cg.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class CustomerConfig {
	
/*	@Autowired
	private UserDetailsService userDetailService;
*/	
	@Autowired
	private CustomerJWTFilter customerJWTFilter;
	
	
//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	} 
//	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(custom->custom.disable());
		httpSecurity.authorizeHttpRequests(request->request
									.requestMatchers("/v1/customer/hello","/v1/bank/**")
									.permitAll()
									.anyRequest().authenticated());
		//httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.addFilterBefore(customerJWTFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	@PostConstruct
	public void init() {
	System.out.println("Customer SecurityConfig Loaded");
	}
	
}

















