package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.SwaggerCodgen.model.FormatResponseOpenApi;
import org.pepo.record.entity.Format;

@Mapper(componentModel = "spring")
public class FormatEntityOpenApiMapper {

    public FormatResponseOpenApi entityToOpenApi(final Format format) {
        final FormatResponseOpenApi formatResponseOpenApi = new FormatResponseOpenApi();
        formatResponseOpenApi.setId(format.getId());
        formatResponseOpenApi.setName(format.getName());
        return formatResponseOpenApi;
    }

    public Format openApiToEntity(final FormatResponseOpenApi formatResponseOpenApi) {
        final Format format = new Format();
        format.setId(formatResponseOpenApi.getId());
        format.setName(formatResponseOpenApi.getName());
        return format;
    }
}
