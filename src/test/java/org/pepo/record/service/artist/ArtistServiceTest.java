package org.pepo.record.service.artist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.ArtistPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;
import org.pepo.record.mapper.ArtistEntityOpenApiMapper;
import org.pepo.record.repository.artist.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

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
    void findAllPaginated() {
        when(artistRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(Page.empty());
        when(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(Mockito.any(Artist.class))).thenReturn(new ArtistResponseOpenApi());
        Iterable<ArtistResponseOpenApi> artistResponseOpenApiIterable = artistService.findAll(0, 3);
        verify(artistRepository, times(1)).findAll(Mockito.any(PageRequest.class));
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

    @Test
    void findByNameLike() {
        when(artistRepository.findByNameLikeIgnoreCase(Mockito.anyString())).thenReturn(new ArrayList<>());
        when(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(Mockito.any(Artist.class))).thenReturn(new ArtistResponseOpenApi());
        List<ArtistResponseOpenApi> artistResponseOpenApiList = artistService.findByNameLike("name");
        verify(artistRepository, times(1)).findByNameLikeIgnoreCase(Mockito.anyString());
        assertNotNull(artistResponseOpenApiList);
    }

    @Test
    void findAllPaged() {
        when(artistRepository.findAll(Mockito.any(Pageable.class))).thenReturn(Page.empty());
        when(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(Mockito.any(Artist.class))).thenReturn(new ArtistResponseOpenApi());
        ArtistPagedResponseOpenApi artistPagedResponseOpenApi = artistService.findAllPaged(0, 5);
        verify(artistRepository, times(1)).findAll(Mockito.any(Pageable.class));
        assertNotNull(artistPagedResponseOpenApi);
    }
}