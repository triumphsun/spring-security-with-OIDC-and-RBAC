package com.suntri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtToeknFilter extends OncePerRequestFilter {

    @Qualifier("impl")
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.printf("[Filter] %s %s?%s", request.getMethod(), request.getRequestURI(), request.getQueryString());
        String token = null;
        Optional<String> headerToken = this.getTokenFromHeader(request);
        if(headerToken.isPresent()){
            token = headerToken.get();
        }

        if(token==null){
            Optional<String> cookieToken = this.getTokenFromCookie(request);
            if(cookieToken.isPresent()){
                token = cookieToken.get();
            }
        }

        if(token==null){
            System.out.printf(" token=null.%n");
            filterChain.doFilter(request, response);
            return;
        } else {
            System.out.printf(" token=%s%n", token);
            Authentication authentication = this.jwtTokenProvider.toAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }

    protected Optional<String> getTokenFromCookie(HttpServletRequest request){
        String line = null;
        Cookie[] cookies = request.getCookies();
        if(cookies==null || cookies.length==0){
            return Optional.empty();
        }
        for(Cookie cookie: cookies){
            if("access_token".equalsIgnoreCase(cookie.getName())){
                line = cookie.getValue();
                break;
            }
        }
        return this.getTokenFromCookie(line);
    }


    protected Optional<String> getTokenFromCookie(String line){
        if(line==null || line==""){
            return Optional.empty();
        }
        int pos = line.indexOf(";");
        if(pos>0){
            return Optional.of(line.substring(0, line.indexOf(";")));
        } else {
            return Optional.of(line);
        }

    }

    protected Optional<String> getTokenFromHeader(HttpServletRequest request){
        String line = request.getHeader(HttpHeaders.AUTHORIZATION);
        return this.getTokenFromHeader(line);
    }

    protected Optional<String> getTokenFromHeader(String line){
        if(line==null || line=="" || !line.startsWith("Bearer ")){
            return Optional.empty();
        }
        String [] split = line.split(" ");
        if(split.length<1){
            return Optional.empty();
        }
        return Optional.of(split[1].trim());
    }
}
