package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.dto.ArtistDto;
import org.pepo.record.entity.Artist;

@Mapper(componentModel = "spring")
abstract public class ArtistEntityDtoMapper {

    public ArtistDto entityToDto(Artist artist) {
        return ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .style(artist.getStyle() != null ? artist.getStyle().getName() : null)
                .nationality(artist.getNationality() != null ? artist.getNationality().getName() : null)
                .build();
    }

}
