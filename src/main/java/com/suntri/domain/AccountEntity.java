package com.suntri.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean enabled;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "account_role",
        joinColumns = { @JoinColumn(name="account_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name="role_id", referencedColumnName = "id") }
    )
    private Set<RoleEntity> roles;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    protected Date createdAt;

    public String getEmail(){
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public Set<RoleEntity> getRoles(){
        return this.roles;
    }

}
