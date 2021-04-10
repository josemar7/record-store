package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.RecordApi;
import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.service.record.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RecordController implements RecordApi {

    @Autowired
    private RecordService recordService;

    @Override
    public ResponseEntity<List<RecordResponseOpenApi>> getAllRecords() {
        List<RecordResponseOpenApi> result =
                StreamSupport.stream(recordService.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
