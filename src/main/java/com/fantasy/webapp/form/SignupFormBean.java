package com.fantasy.webapp.form;

import com.fantasy.webapp.validation.EmailUnique;
import com.fantasy.webapp.validation.UsernameUnique;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupFormBean {

    @EmailUnique
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Username is required")
    @UsernameUnique
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;


}
