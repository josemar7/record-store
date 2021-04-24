package org.pepo.record.service.record;

import lombok.AllArgsConstructor;
import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.entity.Record;
import org.pepo.record.mapper.RecordEntityOpenApiMapper;
import org.pepo.record.repository.record.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {

    @Autowired
    private final RecordRepository recordRepository;

    @Autowired
    private final RecordEntityOpenApiMapper recordEntityOpenApiMapper;

    @Override
    public Iterable<RecordResponseOpenApi> findAll() {
        List<RecordResponseOpenApi> list = new ArrayList<>();
        Iterable<Record> recordIterable = recordRepository.findAll();
        recordIterable.forEach(record -> list.add(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(record)));
        return list;
    }

    @Override
    public RecordResponseOpenApi findById(final int id) {
        Record record = recordRepository.findById(id).orElse(null);
        return recordEntityOpenApiMapper.recordToRecordResponseOpenApi(record);
    }

    @Override
    public RecordResponseOpenApi save(final Record record) {
        return recordEntityOpenApiMapper.recordToRecordResponseOpenApi(recordRepository.save(record));
    }

    @Override
    public RecordResponseOpenApi update(final Record record, final int recordId) {
        record.setId(recordId);
        return recordEntityOpenApiMapper.recordToRecordResponseOpenApi(recordRepository.save(record));
    }

    @Override
    public void delete(final int id) {
        recordRepository.deleteById(id);
    }

    @Override
    public List<RecordResponseOpenApi> filteredRecords(final String name, final String artist, final String format) {
        List<RecordResponseOpenApi> list = new ArrayList<>();
        List<Record> recordList = recordRepository.findFilteredRecords(name, artist, format);
        recordList.forEach(record -> list.add(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(record)));
        return list;
    }
}
