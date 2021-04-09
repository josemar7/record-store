package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.dto.StyleDto;
import org.pepo.record.entity.Style;

@Mapper(componentModel = "spring")
public interface StyleEntityDtoMapper {

    StyleDto styleToStyleDto(Style style);

}
