package com.project.controller;

import com.project.model.Projekt;
import com.project.service.ProjektService;
import com.project.service.StudentService;
import com.project.service.ZadanieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProjectControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjektService projektService;

    @MockBean
    private ZadanieService zadanieService;

    @MockBean
    private StudentService studentService;

    @Test
    void projektList_shouldReturnProjektView_andExposePagingModel() throws Exception {
        Projekt projekt = new Projekt();
        projekt.setProjektId(1);
        projekt.setNazwa("Test");

        given(projektService.getProjekty(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(projekt), PageRequest.of(0, 10), 1));
        given(zadanieService.countZadaniaForProjekt(1)).willReturn(3L);

        mockMvc.perform(get("/projektList").param("page", "0").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("projekt"))
                .andExpect(model().attributeExists("pageProjekty"))
                .andExpect(model().attributeExists("projekty"))
                .andExpect(model().attributeExists("zadaniaCountByProjektId"));
    }
}
