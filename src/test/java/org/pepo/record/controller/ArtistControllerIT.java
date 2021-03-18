package org.pepo.record.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pepo.record.entity.Artist;
import org.pepo.record.service.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class ArtistControllerIT {

    private static final String ARTIST_ROOT = "/artist/";

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @MockBean
    ArtistService artistService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser("spring")
    @Test
    void getAllArtists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "all"))
                .andExpect(status().isForbidden());
    }

    @Test
    void getArtistByIdUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllArtistsWithHttpBasicUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "all")
                .with(httpBasic("user", "password")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAllArtistsWithHttpBasicCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "all")
                .with(httpBasic("scott", "tiger")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getArtistById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "1")
                .with(httpBasic("scott", "tiger")))
                .andExpect(status().is2xxSuccessful());
    }

    @WithUserDetails("scott")
    @Test
    void getArtistByIdSecured() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "secured/5"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAllArtistsWithHttpBasicAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ARTIST_ROOT + "all")
                .with(httpBasic("spring", "pepo")))
                .andExpect(status().is2xxSuccessful());
    }

    @Rollback
    @Test
    void deleteArtistHttpBasic() throws Exception {
        mockMvc.perform(delete(ARTIST_ROOT + "5")
        .with(httpBasic("spring", "pepo")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteArtistHttpBasicCustomerRole() throws Exception {
        mockMvc.perform(delete(ARTIST_ROOT + "1")
                .with(httpBasic("scott", "tiger")))
                .andExpect(status().isForbidden());
    }

    @Test
    void newArtist() throws Exception {
        Artist artist = new Artist();
        ObjectMapper mapper = new ObjectMapper();
        String artistString = mapper.writeValueAsString(artist);
        mockMvc.perform(post(ARTIST_ROOT + "new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(artistString)
                .with(httpBasic("spring", "pepo")))
                .andExpect(status().is2xxSuccessful());
    }

}
