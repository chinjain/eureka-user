package com.dev.user.projectConfig;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		
	

		CorsConfigurationSource source = new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration configuration = new CorsConfiguration();

				configuration.setAllowCredentials(true);
				configuration.setAllowedOriginPatterns(Arrays.asList("*"));
				configuration.setExposedHeaders(Arrays.asList("X-content-Type-Options", "X-XSS-Protection",
						"Content-type", "X-frame-Options", "Transfer-Encoding"));
				configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
				configuration.setAllowedHeaders(Arrays.asList("*"));
				return configuration;
			}
		};

		return new CorsFilter(source);

	}

}
