package org.pepo.record.service.style;

import org.pepo.record.SwaggerCodgen.model.StyleResponseOpenApi;
import org.pepo.record.entity.Style;

public interface StyleService {

    Iterable<StyleResponseOpenApi> findAll();

    StyleResponseOpenApi save(Style style);
}
