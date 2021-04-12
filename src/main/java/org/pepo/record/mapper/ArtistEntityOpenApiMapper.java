package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;

@Mapper(componentModel = "spring")
public interface ArtistEntityOpenApiMapper {

    ArtistResponseOpenApi artistToArtistResponseOpenApi(Artist artist);

    Artist artistResponseOpenApiToArtist(ArtistResponseOpenApi artistResponseOpenApi);
}
