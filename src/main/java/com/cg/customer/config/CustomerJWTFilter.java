package com.cg.customer.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cg.customer.serviceImpl.CustomerJwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomerJWTFilter extends OncePerRequestFilter {

	@Autowired
	CustomerJwtService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader=request.getHeader("Authorization");
		String token=null;
		String username=null;
		String role="USER";
		System.out.println(authHeader);
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {

		    token = authHeader.substring(7);

		    if(jwtService.validateToken(token)) {

		        username = jwtService.extractUsername(token);
		        
System.out.println("---------"+username+"-----------");
		        UsernamePasswordAuthenticationToken auth =
		                new UsernamePasswordAuthenticationToken(
		                        username,
		                        null,
		                        List.of(new SimpleGrantedAuthority(role)));

		        SecurityContextHolder.getContext()
		                .setAuthentication(auth);
		    }
		}

		filterChain.doFilter(request, response);
	}

}
