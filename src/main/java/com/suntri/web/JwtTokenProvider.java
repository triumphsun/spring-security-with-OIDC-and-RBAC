package com.suntri.web;

import org.springframework.security.core.Authentication;

public interface JwtTokenProvider {
    public String generate(Authentication authentication);
    public Authentication toAuthentication(String token);
    public boolean validate(String token);
}
