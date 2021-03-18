package org.pepo.record.service.nationality;

import org.pepo.record.dto.NationalityDto;

public interface NationalityService {

    Iterable<NationalityDto> findAll();

}
