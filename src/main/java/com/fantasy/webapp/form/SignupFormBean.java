package com.fantasy.webapp.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupFormBean {

    // TODO - In-class examples have @EmailUnique, which we created during Spring Security
    // TODO - Add this in later for unique email/username.

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;


}
