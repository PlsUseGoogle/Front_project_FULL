package com.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private final String baseUrl;

    public ApiAuthenticationProvider(@Value("${rest.base.url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials() != null
                ? authentication.getCredentials().toString()
                : "";

        RestClient client = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(headers -> headers.setBasicAuth(username, password))
                .build();

        try {
            client.get()
                    .uri(uriBuilder -> uriBuilder.path("/projekty")
                            .queryParam("page", 0)
                            .queryParam("size", 1)
                            .build())
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            throw new BadCredentialsException("Invalid API credentials", ex);
        } catch (Exception ex) {
            throw new BadCredentialsException("API authentication failed", ex);
        }

        return new UsernamePasswordAuthenticationToken(
                username,
                password,
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
