package com.example.demo.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import com.example.demo.services.TokenService;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

	@Autowired
	private TokenService tokenService;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (tokenService == null) {
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
		}
		String authHeader = request.getHeader("Authorization");
		String token = "";
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String[] part = authHeader.split(" ");
			token = part[1];
		}
		System.out.println(token);
		try {
			Claims claims = tokenService.validatetoken(token);
			request.setAttribute("claims", claims);
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(e.getMessage());
		}
	}
}