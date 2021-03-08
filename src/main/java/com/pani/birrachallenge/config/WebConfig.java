package com.pani.birrachallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pani.birrachallenge.service.user.RestAuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public RestAuthInterceptor restAuthInterceptor() {
		return new RestAuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(restAuthInterceptor());

	}
}
