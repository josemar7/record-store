package org.pepo.record.entity;

import lombok.Getter;
import lombok.Setter;
import org.pepo.record.commons.BaseVO;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "style")
public class Style extends BaseVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstyle")
    private Integer id;

    String name;
}
