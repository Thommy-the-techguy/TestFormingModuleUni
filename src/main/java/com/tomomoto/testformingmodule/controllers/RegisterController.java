package com.tomomoto.testformingmodule.controllers;

import com.tomomoto.testformingmodule.dto.RegistrationForm;
import com.tomomoto.testformingmodule.entities.User;
import com.tomomoto.testformingmodule.enumerates.Role;
import com.tomomoto.testformingmodule.repos.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String register() {
        return "register";
    }

//    @ModelAttribute(name = "user")
//    public User user() {
//        return new User();
//    }

    @ModelAttribute(name = "registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm registrationForm, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }

        log.info("Processing registration form:\nemail - {}\nusername - {}\npassword - {}",
                registrationForm.getEmail(),
                registrationForm.getUsername(),
                registrationForm.getPassword());

        User user = User.builder()
                .email(registrationForm.getEmail())
                .username(registrationForm.getUsername())
                .password(passwordEncoder.encode(registrationForm.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        return "redirect:/login";
    }
}
