package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.JwtFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class FilterConfig {
	

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new JwtFilter());
		filter.addUrlPatterns("/api/employees/*", "/api/departments/*");
		return filter;
	}
}