package com.lxw.lxwapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LxwapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LxwapiGatewayApplication.class, args);
    }



    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("tobaidu", r -> r.path("/baidu")
                        .uri("http://www.baidu.com"))
                .route("toyupiicu", r -> r.path("/yupiicu")
                        .uri("http://yupi.icu"))
                .build();
    }
}
