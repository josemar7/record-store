package org.pepo.record.service.format;

import org.pepo.record.SwaggerCodgen.model.FormatResponseOpenApi;
import org.pepo.record.entity.Format;

public interface FormatService {

    Iterable<FormatResponseOpenApi> findAll();

    FormatResponseOpenApi save(Format format);
}
