package com.suntri.service;

import java.util.Date;
import java.util.Set;

public interface Account {
    public String getEmail();
    public Date getCreatedAt();
    public Set<String> getRoles();
}
