package org.pepo.record.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.pepo.record.dto.ArtistDto;
import org.pepo.record.entity.Artist;

import static org.junit.jupiter.api.Assertions.*;

class ArtistEntityDtoMapperTest {

    private ArtistEntityDtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ArtistEntityDtoMapper.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void entityToDto() {
        Artist artist = new Artist();
        artist.setName("name");
        ArtistDto artistDto = mapper.entityToDto(artist);
        assertEquals(artist.getName(), artistDto.getName());
    }
}