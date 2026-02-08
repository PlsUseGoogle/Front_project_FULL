package com.project.service;

import com.project.model.Projekt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class ProjektServiceRestClientContractTest {

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getProjekty_shouldCallBackendWithPageAndSize() {
        MockServerRestClientCustomizer customizer = new MockServerRestClientCustomizer();
        RestClient.Builder builder = RestClient.builder().baseUrl("http://localhost:8081/api");
        customizer.customize(builder);
        MockRestServiceServer server = customizer.getServer();

        ApiRestClientProvider provider = new ApiRestClientProvider(builder, "http://localhost:8081/api");
        ProjektServiceImpl service = new ProjektServiceImpl(provider);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "user",
                        "pass",
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );

        server.expect(once(), requestTo("http://localhost:8081/api/projekty?page=0&size=10"))
                .andExpect(method(org.springframework.http.HttpMethod.GET))
                .andExpect(queryParam("page", "0"))
                .andExpect(queryParam("size", "10"))
                .andRespond(withSuccess("""
                        {
                          "content":[{"projektId":1,"nazwa":"A"}],
                          "number":0,
                          "size":10,
                          "totalElements":1,
                          "totalPages":1
                        }
                        """, MediaType.APPLICATION_JSON));

        Page<Projekt> page = service.getProjekty(PageRequest.of(0, 10));

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getProjektId()).isEqualTo(1);
        server.verify();
    }
}
