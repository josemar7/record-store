package org.pepo.record.repository.record;

import org.pepo.record.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecordCustomRepository {

    List<Record> findFilteredRecords(String name, String artist, String format, String style);

    Page<Record> findFilteredRecords(String name, String artist, String format, String style, Pageable pageable);
}
