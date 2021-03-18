package org.pepo.record.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artist")
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_nationality")
    private Nationality nationality;

    @ManyToOne
    @JoinColumn(name = "id_style")
    private Style style;

}
