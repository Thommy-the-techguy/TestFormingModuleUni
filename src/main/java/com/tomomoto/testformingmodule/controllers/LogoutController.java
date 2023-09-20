package com.tomomoto.testformingmodule.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/logout")
public class LogoutController {
    @PostMapping
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "welcome";
    }
}
