package org.pepo.record.service.record;

import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.entity.Record;

public interface RecordService {

    Iterable<RecordResponseOpenApi> findAll();

    RecordResponseOpenApi findById(int id);

    RecordResponseOpenApi save(Record record);

    RecordResponseOpenApi update(Record record, int recordId);

    void delete(int id);
}
