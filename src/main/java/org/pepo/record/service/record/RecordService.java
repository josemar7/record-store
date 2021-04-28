package org.pepo.record.service.record;

import org.pepo.record.SwaggerCodgen.model.RecordPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.entity.Record;

import java.util.List;

public interface RecordService {

    Iterable<RecordResponseOpenApi> findAll();

    List<RecordResponseOpenApi> findAll(Integer page, Integer size);

    RecordResponseOpenApi findById(int id);

    RecordResponseOpenApi save(Record record);

    RecordResponseOpenApi update(Record record, int recordId);

    void delete(int id);

    List<RecordResponseOpenApi> filteredRecords(String name, String artist, String format, String style, Integer page, Integer size);

    RecordPagedResponseOpenApi findAllPaged(Integer page, Integer size);
}
