package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.SwaggerCodgen.model.NationalityResponseOpenApi;
import org.pepo.record.entity.Nationality;

@Mapper(componentModel = "spring")
public interface NationalityEntityOpenApiMapper {

    NationalityResponseOpenApi nationalityToNationalityResponseOpenApi(Nationality nationality);

    Nationality nationalityResponseOpenApiToNationality(NationalityResponseOpenApi nationalityResponseOpenApi);
}
