package org.pepo.record.service.artist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.dto.ArtistDto;
import org.pepo.record.entity.Artist;
import org.pepo.record.mapper.ArtistEntityDtoMapper;
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
    ArtistEntityDtoMapper artistEntityDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(artistRepository.findAll()).thenReturn(new ArrayList<>());
        when(artistEntityDtoMapper.entityToDto(Mockito.any(Artist.class))).thenReturn(ArtistDto.builder().build());
        Iterable<ArtistDto> artistDtos = artistService.findAll();
        verify(artistRepository, times(1)).findAll();
        assertNotNull(artistDtos);
    }

    @Test
    void save() {
        when(artistRepository.save(ArgumentMatchers.any(Artist.class))).thenReturn(new Artist());
        when(artistEntityDtoMapper.entityToDto(Mockito.any(Artist.class))).thenReturn(ArtistDto.builder().build());
        ArtistDto artist = artistService.save(new Artist());
        verify(artistEntityDtoMapper, times(1)).entityToDto(Mockito.any(Artist.class));
        verify(artistRepository, times(1)).save(ArgumentMatchers.any(Artist.class));
        assertNotNull(artist);
    }
}