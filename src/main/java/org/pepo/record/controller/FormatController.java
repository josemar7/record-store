package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.FormatApi;
import org.pepo.record.SwaggerCodgen.model.FormatResponseOpenApi;
import org.pepo.record.mapper.FormatEntityOpenApiMapper;
import org.pepo.record.service.format.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class FormatController implements FormatApi {

    @Autowired
    private FormatService formatService;

    @Autowired
    private FormatEntityOpenApiMapper formatEntityOpenApiMapper;

    @Override
    public ResponseEntity<List<FormatResponseOpenApi>> getAllFormats() {
        List<FormatResponseOpenApi> result =
                StreamSupport.stream(formatService.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<FormatResponseOpenApi> newFormat(final FormatResponseOpenApi formatResponseOpenApi) {
        return ResponseEntity.ok(formatService.save(formatEntityOpenApiMapper.openApiToEntity(formatResponseOpenApi)));
    }
}
