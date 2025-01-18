package com.fantasy.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login/login")
    public ModelAndView theLoginPage() {
        ModelAndView response = new ModelAndView();

        response.setViewName("login/loginPage");

        return response;
    }

}
