package com.suntri.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("/views/welcome-guest");
        return mnv;
    }

}
