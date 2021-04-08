package org.pepo.record.service.nationality;

import org.pepo.record.dto.NationalityDto;
import org.pepo.record.entity.Nationality;

public interface NationalityService {

    Iterable<NationalityDto> findAll();

    NationalityDto save(Nationality nationality);
}
