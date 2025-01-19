package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.UserDAO;
import com.fantasy.webapp.database.entity.User;
import com.fantasy.webapp.form.SignupFormBean;
import com.fantasy.webapp.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/login/login")
    public ModelAndView theLoginPage() {
        ModelAndView response = new ModelAndView();

        response.setViewName("login/loginPage");

        return response;
    }

    @GetMapping("/login/register")
    public ModelAndView register(){
        ModelAndView response = new ModelAndView();

        response.setViewName("login/register");

        return response;
    }

    @PostMapping("/login/registrationSubmit")
    public ModelAndView registrationSubmit(@Valid SignupFormBean form, BindingResult bindingResult, HttpSession session) {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            response.setViewName("login/register");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
        } else {
            User user = new User();

            user.setUsername(form.getUsername());
            user.setEmail(form.getEmail());

            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);

            userDAO.save(user);

            authenticatedUserService.changeLoggedInUsername(session, form.getUsername(), form.getPassword());

            response.setViewName("redirect:/");
        }

        return response;
    }

}
