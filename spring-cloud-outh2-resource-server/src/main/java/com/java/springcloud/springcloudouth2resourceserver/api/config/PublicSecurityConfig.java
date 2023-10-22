package com.java.springcloud.springcloudouth2resourceserver.api.config;

import java.util.stream.Collectors;

import com.java.springcloud.springcloudouth2resourceserver.api.config.properties.CustomGatewayProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Configuration class for Unsecured Urls i.e. URLs which are exposed without any security.
 * This can be turned-off globally using property {@code custom.gateway.expose-unsecured-urls}
 * 
 * @author akash gupta
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(value = "custom.gateway.expose-unsecured-urls", havingValue = "true", matchIfMissing = false)
public class PublicSecurityConfig {
	
	private final @NonNull CustomGatewayProperties gatewayCustomProperties;
	
	/**
	 * Creates {@code SecurityWebFilterChain} for unsecured URLs
	 * 
	 * @param httpSecurity to configure
	 * @return {@code SecurityWebFilterChain}
	 * @throws Exception
	 */
	@Bean
	@Order(1)
	public SecurityWebFilterChain configurePublicUrlSecurity(ServerHttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity
				.securityMatcher(getServerWebExchangeMatcher())
				.authorizeExchange()
					.anyExchange().permitAll()
				.and()
					.httpBasic()
						.disable()
					.formLogin()
						.disable()
					.csrf()
						.disable()
					.cors()
						.disable()
				.build();
	}
	
	/**
	 * Creates a {@link OrServerWebExchangeMatcher} with unsecured URLs present in application properties
	 * @return {@code ServerWebExchangeMatcher}
	 */
	private ServerWebExchangeMatcher getServerWebExchangeMatcher() {

		return new OrServerWebExchangeMatcher(
				gatewayCustomProperties.getUnsecuredUrls()
				.stream()
				.map(unsecuredUrl -> new PathPatternParserServerWebExchangeMatcher(unsecuredUrl))
				.collect(Collectors.toList())
				);
	}
}
