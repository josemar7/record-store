package org.pepo.record.repository.record;

import org.pepo.record.entity.Record;

import java.util.List;

public interface RecordCustomRepository {

    List<Record> findFilteredRecords(String name, String artist, String format, String style);
}
