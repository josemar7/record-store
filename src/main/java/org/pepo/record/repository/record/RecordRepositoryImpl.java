package org.pepo.record.repository.record;

import org.apache.commons.lang3.StringUtils;
import org.pepo.record.entity.Artist_;
import org.pepo.record.entity.Format_;
import org.pepo.record.entity.Record;
import org.pepo.record.entity.Record_;
import org.pepo.record.entity.Style_;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RecordRepositoryImpl implements RecordCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Record> findFilteredRecords(final String name, final String artist, final String format, final String style) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Record> cq = cb.createQuery(Record.class);
        Root<Record> record = cq.from(Record.class);
        Join<Object, Object> artistJoin = record.join(Record_.ARTIST);
        Join<Object, Object> formatJoin = record.join(Record_.FORMAT);
        Join<Object, Object> styleJoin = artistJoin.join(Artist_.STYLE);
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            predicates.add(cb.like(cb.lower(record.get(Record_.NAME)), "%" + name.toLowerCase() + "%"));
        }
        if (StringUtils.isNotBlank(artist)) {
            predicates.add(cb.like(cb.lower(artistJoin.get(Artist_.NAME)), "%" + artist.toLowerCase() + "%"));
        }
        if (StringUtils.isNotBlank(format)) {
            predicates.add(cb.equal(cb.lower(formatJoin.get(Format_.NAME)), format.toLowerCase()));
        }
        if (StringUtils.isNotBlank(style)) {
            predicates.add(cb.equal(cb.lower(styleJoin.get(Style_.NAME)), style.toLowerCase()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
