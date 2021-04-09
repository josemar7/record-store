package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.SwaggerCodgen.model.FormatResponseOpenApi;
import org.pepo.record.entity.Format;

@Mapper(componentModel = "spring")
public interface FormatEntityOpenApiMapper {

    FormatResponseOpenApi formatToFormatResponseOpenApi(Format format);

    Format formatResponseOpenApiToFormat(FormatResponseOpenApi formatResponseOpenApi);
}
