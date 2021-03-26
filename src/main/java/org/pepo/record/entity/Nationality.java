package org.pepo.record.entity;

import lombok.Getter;
import lombok.Setter;
import org.pepo.record.commons.BaseVO;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "nationality")
public class Nationality extends BaseVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnationality")
    private Integer id;

    private String name;

}
