package org.pepo.record.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.FormatResponseOpenApi;
import org.pepo.record.entity.Format;
import org.pepo.record.mapper.FormatEntityOpenApiMapper;
import org.pepo.record.service.format.FormatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class FormatControllerTest {

    @InjectMocks
    FormatController formatController;

    @Mock
    FormatService formatService;

    @Mock
    FormatEntityOpenApiMapper formatEntityOpenApiMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(formatController).build();
    }

    @Test
    void getAllFormats() throws Exception {
        when(formatService.findAll())
                .thenReturn(new ArrayList<>());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/format/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(formatService, times(1)).findAll();
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void newFormat() throws Exception {
        FormatResponseOpenApi formatResponseOpenApi = new FormatResponseOpenApi();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String formatString = mapper.writeValueAsString(formatResponseOpenApi);
        when(formatService.save(Mockito.any(Format.class))).thenReturn(new FormatResponseOpenApi());
        ResultActions result = mockMvc.perform(post("/format/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formatString));
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }
}