package org.pepo.record.repository.record;

import org.pepo.record.entity.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Integer>, RecordCustomRepository {
}
