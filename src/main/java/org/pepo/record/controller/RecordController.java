package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.RecordApi;
import org.pepo.record.SwaggerCodgen.model.RecordPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.mapper.RecordEntityOpenApiMapper;
import org.pepo.record.service.record.RecordService;
import org.pepo.record.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RecordController implements RecordApi {

    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordEntityOpenApiMapper recordEntityOpenApiMapper;

    @Override
    public ResponseEntity<List<RecordResponseOpenApi>> getAllRecords(final Integer page, final Integer size) {
        List<RecordResponseOpenApi> result =
                StreamSupport.stream(recordService.findAll(page, size).spliterator(), false)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteRecordById(final Integer recordId) {
        recordService.delete(recordId);
        return ResponseUtils.createResponse(null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<RecordResponseOpenApi> getRecordById(final Integer recordId) {
        return ResponseEntity.ok(recordService.findById(recordId));
    }

    @Override
    public ResponseEntity<RecordResponseOpenApi> newRecord(RecordResponseOpenApi recordResponseOpenApi) {
        return ResponseEntity.ok(recordService.save(recordEntityOpenApiMapper.recordResponseOpenApiToRecord(recordResponseOpenApi)));
    }

    @Override
    public ResponseEntity<RecordResponseOpenApi> updateRecordById(Integer recordId, RecordResponseOpenApi recordResponseOpenApi) {
        return ResponseEntity.ok(recordService.update(recordEntityOpenApiMapper.recordResponseOpenApiToRecord(recordResponseOpenApi), recordId));
    }

    @Override
    public ResponseEntity<RecordPagedResponseOpenApi> getFilterRecords(final String name, final String artist, final String format, final String style,
                                                                        final Integer page, final Integer size) {
        return ResponseEntity.ok(recordService.filteredRecords(name, artist, format, style, page, size));
    }

    @Override
    public ResponseEntity<RecordPagedResponseOpenApi> getAllRecordsPaged(final Integer page, final Integer size) {
        return ResponseEntity.ok(recordService.findAllPaged(page, size));
    }
}
