package com.pigihi.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class AuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String role = request.getHeader("X-USER-ROLE");
		String userId = request.getHeader("X-USER-ID");
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(authority);
		Authentication authenticatedToken = new UsernamePasswordAuthenticationToken(userId, authority, authorities);
		SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
		
		filterChain.doFilter(request, response);
	}

}
