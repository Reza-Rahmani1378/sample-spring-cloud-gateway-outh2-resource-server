package com.java.springcloud.springcloudouth2resourceserver.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/welcome")
public class WelcomeController {


    @GetMapping("/unsecured-hello")
    public Mono<String> sayHelloUnsecured() {
        return Mono.just("Hello! I am unsecured and can be accessed without any security.");
    }

    @GetMapping("/secured-hello")
    public Mono<String> sayHelloSecured() {
        return Mono.just("Hello! I am secured and can only accessed with valid access token.");
    }
}
