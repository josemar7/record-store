package org.pepo.record.entity.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
}
