package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.StyleApi;
import org.pepo.record.SwaggerCodgen.model.StyleResponseOpenApi;
import org.pepo.record.mapper.StyleEntityOpenApiMapper;
import org.pepo.record.service.style.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class StyleController implements StyleApi {

    @Autowired
    private StyleService styleService;

    @Autowired
    private StyleEntityOpenApiMapper styleEntityOpenApiMapper;

    @Override
    public ResponseEntity<List<StyleResponseOpenApi>> getAllStyles() {
        List<StyleResponseOpenApi> result =
                StreamSupport.stream(styleService.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<StyleResponseOpenApi> newStyle(StyleResponseOpenApi styleResponseOpenApi) {
        return ResponseEntity.ok(styleService.save(styleEntityOpenApiMapper.styleResponseOpenApiToStyle(styleResponseOpenApi)));
    }
}
