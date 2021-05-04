package com.suntri.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AccountImpl implements Account {

    private String email;
    private Date createdAt;
    private Set<String> roles = new HashSet<>();

    protected AccountImpl(){

    }

    @Override
    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    public void addRoles(String role) {
        this.roles.add(role);
    }
}
