package com.utcn.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utcn.demo.model.Expert;
import com.utcn.demo.service.ExpertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExpertController.class)
public class ExpertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ExpertService expertService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    void testGetAllExperts() throws Exception {
        Expert expert1 = new Expert(1L, "Doe", "John");
        Expert expert2 = new Expert(2L, "Smith", "Anna");
        List<Expert> experts = Arrays.asList(expert1, expert2);

        when(expertService.retrieveExperts()).thenReturn(experts);

        mockMvc.perform(get("/experts/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(experts)));
    }

    @Test
    @WithMockUser
    void testGetExpertById() throws Exception {
        Long expertId = 1L;
        Expert expert = new Expert(expertId, "Doe", "John");

        when(expertService.retrieveExpertById(expertId)).thenReturn(expert);

        mockMvc.perform(get("/experts/getById/{id}", expertId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expert)));
    }

    @Test
    @WithMockUser
    void testCreateExpert() throws Exception {
        Expert expert = new Expert(null, "New", "Expert");
        Expert savedExpert = new Expert(1L, "New", "Expert");

        when(expertService.addExpert(any(Expert.class))).thenReturn(savedExpert);

        mockMvc.perform(post("/experts/createExpert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(expert)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(savedExpert)));
    }

    @Test
    @WithMockUser
    void testDeleteExpertById() throws Exception {
        Long expertId = 1L;
        String successMessage = "Expert deleted successfully";

        when(expertService.deleteExpertById(expertId)).thenReturn(successMessage);

        mockMvc.perform(delete("/experts/deleteById")
                        .param("id", expertId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(successMessage));
    }
}
