package com.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ApiRestClientProvider {

    private final RestClient.Builder restClientBuilder;
    private final String baseUrl;

    public ApiRestClientProvider(RestClient.Builder restClientBuilder,
                                 @Value("${rest.base.url}") String baseUrl) {
        this.restClientBuilder = restClientBuilder;
        this.baseUrl = baseUrl;
    }

    public RestClient clientForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName() == null || auth.getName().isBlank()) {
            throw new IllegalStateException("No authenticated user available for API call");
        }
        if (auth.getCredentials() == null) {
            throw new IllegalStateException("No credentials available for API call");
        }

        return restClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .defaultHeaders(headers -> {
                    headers.setBasicAuth(auth.getName(), auth.getCredentials().toString());
                    headers.set(HttpHeaders.ACCEPT, "application/json");
                    headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
                })
                .build();
    }

    public RestClient clientForAnonymous() {
        return restClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .defaultHeaders(headers -> {
                    headers.set(HttpHeaders.ACCEPT, "application/json");
                    headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
                })
                .build();
    }
}
