package org.pepo.record.service.style;

import org.pepo.record.dto.StyleDto;
import org.pepo.record.entity.Style;

public interface StyleService {

    Iterable<StyleDto> findAll();

    StyleDto save(Style style);
}
