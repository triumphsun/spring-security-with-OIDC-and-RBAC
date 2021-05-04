package com.suntri.web;

import java.util.HashSet;
import java.util.Set;

public class AccountDto {
    private String email;
    private String createdAt;
    private Set<String> roles = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void addRoles(String role) {
        this.roles.add(role);
    }
}
