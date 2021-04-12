package org.pepo.record.service.nationality;

import lombok.AllArgsConstructor;
import org.pepo.record.SwaggerCodgen.model.NationalityResponseOpenApi;
import org.pepo.record.entity.Nationality;
import org.pepo.record.mapper.NationalityEntityOpenApiMapper;
import org.pepo.record.repository.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NationalityServiceImpl implements NationalityService {

    @Autowired
    private final NationalityEntityOpenApiMapper nationalityEntityOpenApiMapper;

    @Autowired
    private final NationalityRepository nationalityRepository;

    @Override
    public Iterable<NationalityResponseOpenApi> findAll() {
        List<NationalityResponseOpenApi> list = new ArrayList<>();
        Iterable<Nationality> nationalityIterable = nationalityRepository.findAll();
        nationalityIterable.forEach(nationality -> list.add(nationalityEntityOpenApiMapper.nationalityToNationalityResponseOpenApi(nationality)));
        return list;
    }

    @Override
    public NationalityResponseOpenApi save(final Nationality nationality) {
        return nationalityEntityOpenApiMapper.nationalityToNationalityResponseOpenApi(nationalityRepository.save(nationality));
    }
}
