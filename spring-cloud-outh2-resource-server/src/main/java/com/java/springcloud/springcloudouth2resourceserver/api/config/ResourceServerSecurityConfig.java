package com.java.springcloud.springcloudouth2resourceserver.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuration class for securing URLs using oauth2 resource server config. 
 * 
 * @author akash gupta
 */
@Configuration
public class ResourceServerSecurityConfig {

	/**
	 * Creates {@code SecurityWebFilterChain} for protecting URLs with oauth2 resource server.
	 * If URL is not part of {@code custom.gateway.unsecured-urls}, it is secured by default.
	 * 
	 * @param httpSecurity to configure
	 * @return {@code SecurityWebFilterChain}
	 * @throws Exception
	 */
	@Bean
	@Order(10)
	public SecurityWebFilterChain configureResourceServer(ServerHttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity
				.authorizeExchange()
					.anyExchange().authenticated()
				.and()
					.oauth2ResourceServer().jwt().and()
				.and().build();
	}
}
