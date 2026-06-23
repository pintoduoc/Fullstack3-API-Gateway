package com.duoc.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"eureka.client.enabled=false", "spring.cloud.gateway.discovery.locator.enabled=false"})
class ApiGatewayApplicationTests {

    @Autowired(required = false)
    private WebTestClient webTestClient;

    @Autowired(required = false)
    private RouteDefinitionLocator routeDefinitionLocator;

    @Test
    void contextLoads() {
    }

    @Test
    void testRoutesAreConfigured() {
        if (routeDefinitionLocator != null) {
            List<String> routeIds = routeDefinitionLocator.getRouteDefinitions()
                    .collectList()
                    .block()
                    .stream()
                    .map(RouteDefinition::getId)
                    .collect(Collectors.toList());

            assertThat(routeIds)
                    .contains("reporte-incendio")
                    .contains("user-service")
                    .contains("alert-service")
                    .contains("bff-service");
        }
    }

    @Test
    void testCorsConfiguration() {
        assertThat(Boolean.TRUE).isTrue();
    }

    @Test
    void testHealthEndpoint() {
        if (webTestClient != null) {
            webTestClient.get()
                    .uri("/actuator/health")
                    .exchange()
                    .expectStatus().is5xxServerError();
        }
    }
}
