package org.pepo.record.entity;

import lombok.Getter;
import lombok.Setter;
import org.pepo.record.commons.BaseVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "format")
public class Format extends BaseVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idformat")
    private Integer id;

    private String name;
}
