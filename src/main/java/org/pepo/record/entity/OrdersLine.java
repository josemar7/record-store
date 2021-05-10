package org.pepo.record.entity;

import lombok.Getter;
import lombok.Setter;
import org.pepo.record.commons.BaseVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "orders_line")
public class OrdersLine extends BaseVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder_line")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idorder")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "idrecord")
    private Record record;

    private int units;
}
