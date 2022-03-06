package com.ssafy.house.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.house.interceptor.JwtInterceptor;


public class JwtConfig implements WebMvcConfigurer {
	
	@Autowired
	JwtInterceptor ji;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) { // 인터셉터 등록
		
		registry.addInterceptor(ji).addPathPatterns("/**")// Interceptor가 적용될 경로
		.excludePathPatterns(Arrays.asList("/user/login", "/user/insert"));// Interceptor가 적용되지 않을 경로
		
//		.excludePathPatterns(Arrays.asList("","",));
		
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) { // client에서 header추출이 가능하도록 하기 위해 등록
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("access-token");
	}
}

