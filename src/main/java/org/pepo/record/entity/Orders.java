package org.pepo.record.entity;

import lombok.Getter;
import lombok.Setter;
import org.pepo.record.commons.BaseVO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders extends BaseVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private Integer id;

    @OneToMany(mappedBy = "orders", cascade = {CascadeType.PERSIST})
    private List<OrdersLine> ordersLines;

    private String user;

    private LocalDateTime date;
}
