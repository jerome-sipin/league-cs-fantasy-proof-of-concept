package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.UserDAO;
import com.fantasy.webapp.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/user/search")
    public ModelAndView search(@RequestParam(required = false) String username) {
        ModelAndView response = new ModelAndView();

        response.setViewName("user/search");

        response.addObject("search", username);

        // TODO - IMPORTANT
        // Ask Eric about this. Wouldn't it be kind of insecure to do a query that puts passwords into the response
        // object? How would I fix this? I assume the correct answer would be to make this query select just the
        // username.
        if (username != null) {
            List<User> users = userDAO.findByUsernameContainingIgnoreCase(username);
            response.addObject("usersKey", users);
        }
        return response;
    }
}
