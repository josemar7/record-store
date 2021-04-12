package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.NationalityApi;
import org.pepo.record.SwaggerCodgen.model.NationalityResponseOpenApi;
import org.pepo.record.mapper.NationalityEntityOpenApiMapper;
import org.pepo.record.service.nationality.NationalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class NationalityController implements NationalityApi {

    @Autowired
    private NationalityService nationalityService;

    @Autowired
    private NationalityEntityOpenApiMapper nationalityEntityOpenApiMapper;

    @Override
    public ResponseEntity<List<NationalityResponseOpenApi>> getAllNationalities() {
        List<NationalityResponseOpenApi> result =
                StreamSupport.stream(nationalityService.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<NationalityResponseOpenApi> newNationality(NationalityResponseOpenApi nationalityResponseOpenApi) {
        return ResponseEntity.ok(nationalityService.save(nationalityEntityOpenApiMapper.nationalityResponseOpenApiToNationality(nationalityResponseOpenApi)));
    }
}
