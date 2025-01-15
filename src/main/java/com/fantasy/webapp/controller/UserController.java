package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users/search")
    public ModelAndView search(@RequestParam(required = false) String username) {
        ModelAndView response = new ModelAndView();

        response.addObject("search", username);
        
        return response;
    }
}
