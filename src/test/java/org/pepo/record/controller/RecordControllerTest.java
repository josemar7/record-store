package org.pepo.record.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.entity.Record;
import org.pepo.record.mapper.RecordEntityOpenApiMapper;
import org.pepo.record.service.record.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecordControllerTest {

    @InjectMocks
    RecordController recordController;

    @Mock
    RecordService recordService;

    @Mock
    RecordEntityOpenApiMapper recordEntityOpenApiMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recordController).build();
    }

    @Test
    void getAllRecords() throws Exception {
        when(recordService.findAll())
                .thenReturn(new ArrayList<>());
        ResultActions result = mockMvc.perform(get("/record/all"))
                .andExpect(status().isOk());
        verify(recordService, times(1)).findAll();
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void deleteRecordById() throws Exception {
        doNothing().when(recordService).delete(Mockito.anyInt());
        ResultActions result = mockMvc.perform(delete("/record/1")).andExpect(status().is2xxSuccessful());
        verify(recordService, times(1)).delete(Mockito.anyInt());
        assertEquals(HttpStatus.NO_CONTENT.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void getRecordById() throws Exception {
        when(recordService.findById(Mockito.anyInt())).thenReturn(new RecordResponseOpenApi());
        ResultActions result = mockMvc.perform(get("/record/1")).andExpect(status().isOk());
        verify(recordService, times(1)).findById(Mockito.anyInt());
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void newRecord() throws Exception {
        Record record = new Record();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String recordString = mapper.writeValueAsString(record);
        when(recordService.save(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        ResultActions result = mockMvc.perform(post("/record/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(recordString));
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void updateRecordById() throws Exception {
        Record record = new Record();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String recordString = mapper.writeValueAsString(record);
        ResultActions result = mockMvc.perform(put("/record/1").contentType(MediaType.APPLICATION_JSON).content(recordString));
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void getFilterRecords() throws Exception {
        when(recordService.filteredRecords(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new ArrayList<>());
        ResultActions result = mockMvc.perform(get("/record/filter")
                .param("name","name").param("artist", "artist").param("format", "format")
                .param("style", "style"))
                .andExpect(status().isOk());
        verify(recordService, times(1))
                .filteredRecords(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }
}