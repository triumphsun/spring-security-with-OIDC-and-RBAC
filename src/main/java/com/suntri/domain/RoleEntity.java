package com.suntri.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="ROLE")
public class RoleEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AccountEntity> accounts;

    public String getName(){
        return this.name;
    }
}
