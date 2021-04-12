package org.pepo.record.service.nationality;

import org.pepo.record.SwaggerCodgen.model.NationalityResponseOpenApi;
import org.pepo.record.entity.Nationality;

public interface NationalityService {

    Iterable<NationalityResponseOpenApi> findAll();

    NationalityResponseOpenApi save(Nationality nationality);
}
