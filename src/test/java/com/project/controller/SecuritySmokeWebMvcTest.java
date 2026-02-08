package com.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecuritySmokeWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unauthenticatedUser_shouldBeRedirectedToLogin_onProjektList() throws Exception {
        mockMvc.perform(get("/projektList"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("**/index"));
    }
}
