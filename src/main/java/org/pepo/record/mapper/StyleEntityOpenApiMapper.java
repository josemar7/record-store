package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.SwaggerCodgen.model.StyleResponseOpenApi;
import org.pepo.record.entity.Style;

@Mapper(componentModel = "spring")
public interface StyleEntityOpenApiMapper {

    StyleResponseOpenApi styleToStyleResponseOpenApi(Style style);

    Style styleResponseOpenApiToStyle(StyleResponseOpenApi styleResponseOpenApi);
}
