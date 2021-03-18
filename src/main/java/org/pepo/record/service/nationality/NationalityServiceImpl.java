package org.pepo.record.service.nationality;

import lombok.AllArgsConstructor;
import org.pepo.record.dto.NationalityDto;
import org.pepo.record.entity.Nationality;
import org.pepo.record.mapper.NationalityEntityDtoMapper;
import org.pepo.record.repository.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NationalityServiceImpl implements NationalityService {

    @Autowired
    private final NationalityEntityDtoMapper nationalityEntityDtoMapper;

    @Autowired
    private final NationalityRepository nationalityRepository;

    @Override
    public Iterable<NationalityDto> findAll() {
        List<NationalityDto> list = new ArrayList<>();
        Iterable<Nationality> nationalityIterable = nationalityRepository.findAll();
        nationalityIterable.forEach(nationality -> list.add(nationalityEntityDtoMapper.entityToDto(nationality)));
        return list;
    }

}
