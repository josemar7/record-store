package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.dto.StyleDto;
import org.pepo.record.entity.Style;

@Mapper(componentModel = "spring")
abstract public class StyleEntityDtoMapper {

    public StyleDto entityToDto(Style style) {
        return StyleDto.builder()
                .id(style.getId())
                .name(style.getName())
                .build();
    }

}
