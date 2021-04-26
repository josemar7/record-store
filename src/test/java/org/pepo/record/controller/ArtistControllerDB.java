package org.pepo.record.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pepo.record.mapper.ArtistEntityOpenApiMapper;
import org.pepo.record.repository.artist.ArtistRepository;
import org.pepo.record.service.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class ArtistControllerDB {

    private static final String ARTIST_ROOT = "/artist/";

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Autowired
    ArtistService artistService;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtistEntityOpenApiMapper artistEntityOpenApiMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @WithUserDetails("scott")
    @Test
    void getArtistByIdSecured() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "secured/6"))
                .andExpect(status().is2xxSuccessful());
    }

}
