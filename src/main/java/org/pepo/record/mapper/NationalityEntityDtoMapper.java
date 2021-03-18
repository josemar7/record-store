package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.dto.NationalityDto;
import org.pepo.record.entity.Nationality;

@Mapper(componentModel = "spring")
abstract public class NationalityEntityDtoMapper {

    public NationalityDto entityToDto(Nationality nationality) {
        return NationalityDto.builder()
                .id(nationality.getId())
                .name(nationality.getName())
                .build();
    }

}
