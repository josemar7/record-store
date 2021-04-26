package org.pepo.record.repository.record;

import org.pepo.record.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecordRepository extends PagingAndSortingRepository<Record, Integer>, RecordCustomRepository {

    Page<Record> findAll(Pageable pageable);
}