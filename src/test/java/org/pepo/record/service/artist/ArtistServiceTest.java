package org.pepo.record.service.artist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;
import org.pepo.record.mapper.ArtistEntityOpenApiMapper;
import org.pepo.record.repository.ArtistRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ArtistServiceTest {

    @InjectMocks
    ArtistServiceImpl artistService;

    @Mock
    ArtistRepository artistRepository;

    @Mock
    ArtistEntityOpenApiMapper artistEntityOpenApiMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(artistRepository.findAll()).thenReturn(new ArrayList<>());
        when(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(Mockito.any(Artist.class))).thenReturn(new ArtistResponseOpenApi());
        Iterable<ArtistResponseOpenApi> artistResponseOpenApiIterable = artistService.findAll();
        verify(artistRepository, times(1)).findAll();
        assertNotNull(artistResponseOpenApiIterable);
    }

    @Test
    void save() {
        when(artistRepository.save(ArgumentMatchers.any(Artist.class))).thenReturn(new Artist());
        when(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(Mockito.any(Artist.class))).thenReturn(new ArtistResponseOpenApi());
        ArtistResponseOpenApi artistResponseOpenApi = artistService.save(new Artist());
        verify(artistEntityOpenApiMapper, times(1)).artistToArtistResponseOpenApi(Mockito.any(Artist.class));
        verify(artistRepository, times(1)).save(ArgumentMatchers.any(Artist.class));
        assertNotNull(artistResponseOpenApi);
    }
}