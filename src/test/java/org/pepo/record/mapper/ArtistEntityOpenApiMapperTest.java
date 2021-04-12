package org.pepo.record.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;

import static org.junit.jupiter.api.Assertions.*;

class ArtistEntityOpenApiMapperTest {

    private ArtistEntityOpenApiMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ArtistEntityOpenApiMapper.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void artistToArtistDto() {
        Artist artist = new Artist();
        artist.setName("name");
        ArtistResponseOpenApi artistResponseOpenApi = mapper.artistToArtistResponseOpenApi(artist);
        assertEquals(artist.getName(), artistResponseOpenApi.getName());
    }
}