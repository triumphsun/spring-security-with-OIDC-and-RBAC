package com.suntri.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Component("impl")
public class JwtTokenProviderImpl implements JwtTokenProvider {

    @Value("${my.jwt.key}")
    private String key;
    private Algorithm algorithm;
    private JWTVerifier verifier;

    public JwtTokenProviderImpl(){

    }

    public JwtTokenProviderImpl(String key){
        this.key = key;
        this.init();
    }

    @PostConstruct
    public void init(){
        this.algorithm = Algorithm.HMAC256(this.key);
        this.verifier = JWT.require(this.algorithm).build();
    }

    @Override
    public String generate(Authentication authentication) {
        String email = authentication.getName();
        Set<String> roles = new LinkedHashSet<>();
        for(GrantedAuthority authority: authentication.getAuthorities()){
            roles.add(authority.getAuthority());
        }
        String token = JWT.create()
                            .withIssuedAt(new Date())
                            .withClaim("email", authentication.getName())
                            .withArrayClaim("roles", roles.toArray(new String[0]))
                            .sign(this.algorithm);
        return token;
    }

    @Override
    public Authentication toAuthentication(String token) {
        try {
            DecodedJWT jwt = this.verifier.verify(token);
            Date issuedAt = jwt.getIssuedAt();
            String email = jwt.getClaim("email").asString();
            String [] roles = jwt.getClaim("roles").asArray(String.class);
            Set<GrantedAuthority> authorities = new LinkedHashSet<>();
            for(String role: roles){
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return new UsernamePasswordAuthenticationToken(email, null, authorities);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(String token) {
        try {
            DecodedJWT jwt = this.verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

}
