package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.entity.User;
import com.fantasy.webapp.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView response = new ModelAndView();

        User currentUser = authenticatedUserService.loadCurrentUser();
        if (currentUser != null) {
            String user = currentUser.getUsername();
            response.addObject("currentUser", user);
        } else {
            String user = "Guest";
            response.addObject("currentUser", user);
        }
        response.setViewName("index");


        return response;
    }
}
