package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.entity.Record;

@Mapper(componentModel = "spring")
public interface RecordEntityOpenApiMapper {

    RecordResponseOpenApi recordToRecordResponseOpenApi(Record record);

    Record recordResponseOpenApiToRecord(RecordResponseOpenApi recordResponseOpenApi);
}
