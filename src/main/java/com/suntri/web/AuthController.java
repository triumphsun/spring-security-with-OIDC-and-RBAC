package com.suntri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("impl")
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView sign(){
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("/views/sign-in");
        return mnv;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("/views/sign-in");
        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        response.addHeader("Set-Cookie", String.format("access_token=%s; Secure; HttpOnly;", this.jwtTokenProvider.generate(authentication)));
        mnv.setViewName("redirect:/");
        return mnv;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletResponse response){
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("redirect:/");
        response.addHeader("Set-Cookie", String.format("access_token=%s; Secure; HttpOnly;", "deleted"));
        return mnv;
    }
}
