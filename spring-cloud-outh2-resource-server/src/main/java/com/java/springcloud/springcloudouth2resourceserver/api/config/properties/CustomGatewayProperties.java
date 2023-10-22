package com.java.springcloud.springcloudouth2resourceserver.api.config.properties;


import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@RefreshScope
@Data
@Slf4j
@Configuration
@ConfigurationProperties("custom.gateway")
public class CustomGatewayProperties {

    private boolean exposeUnsecuredUrls = false;
    private List<String> unsecuredUrls = new ArrayList<>();

    @PostConstruct
    public void logGatewayCustomProperties() {
        log.info("***** Gateway Custom Properties: {} *****", toString());
    }
}