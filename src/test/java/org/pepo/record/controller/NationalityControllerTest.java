package org.pepo.record.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepo.record.service.nationality.NationalityService;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NationalityControllerTest {

    @InjectMocks
    NationalityController nationalityController;

    @Mock
    NationalityService nationalityService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(nationalityController).build();
    }

    @Test
    void getAllNationalities() throws Exception {
        when(nationalityService.findAll())
                .thenReturn(new ArrayList<>());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/nationality/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(nationalityService, times(1)).findAll();
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

}