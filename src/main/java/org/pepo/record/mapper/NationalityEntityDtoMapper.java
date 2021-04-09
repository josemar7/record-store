package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.dto.NationalityDto;
import org.pepo.record.entity.Nationality;

@Mapper(componentModel = "spring")
public interface NationalityEntityDtoMapper {

    NationalityDto nationalityToNationalityDto(Nationality nationality);
}
