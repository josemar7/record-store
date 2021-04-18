package org.pepo.record.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;
import org.pepo.record.mapper.ArtistEntityOpenApiMapper;
import org.pepo.record.service.artist.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArtistControllerTest {

    @InjectMocks
    ArtistController artistController;

    @Mock
    ArtistService artistService;

    @Mock
    ArtistEntityOpenApiMapper artistEntityOpenApiMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(artistController).build();
    }

    @Test
    void getAllArtists() throws Exception {
        when(artistService.findAll())
                .thenReturn(new ArrayList<>());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/artist/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(artistService, times(1)).findAll();
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void getArtistById() throws Exception {
        when(artistService.findById(Mockito.anyInt()))
                .thenReturn(new ArtistResponseOpenApi());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/artist/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(artistService, times(1)).findById(Mockito.anyInt());
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void newArtist() throws Exception {
        Artist artist = new Artist();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String artistString = mapper.writeValueAsString(artist);
        when(artistService.save(Mockito.any(Artist.class))).thenReturn(new ArtistResponseOpenApi());
        ResultActions result = mockMvc.perform(post("/artist/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(artistString));
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void deleteArtistById() throws Exception {
        doNothing().when(artistService).delete(Mockito.anyInt());
        ResultActions result = mockMvc.perform(delete("/artist/1")).andExpect(status().is2xxSuccessful());
        verify(artistService, times(1)).delete(Mockito.anyInt());
        assertEquals(HttpStatus.NO_CONTENT.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void updateArtistById() throws Exception {
        Artist artist = new Artist();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String artistString = mapper.writeValueAsString(artist);
        ResultActions result = mockMvc.perform(put("/artist/1").contentType(MediaType.APPLICATION_JSON).content(artistString));
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }

    @Test
    void getFilterArtists() throws Exception {
        when(artistService.findByNameLike(Mockito.anyString()))
                .thenReturn(new ArrayList<>());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/artist/filter")
                .param("name", "name"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(artistService, times(1)).findByNameLike(Mockito.anyString());
        assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
    }
}