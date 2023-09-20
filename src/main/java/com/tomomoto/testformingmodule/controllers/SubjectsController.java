package com.tomomoto.testformingmodule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subjects")
public class SubjectsController {
    @GetMapping
    public String getSubjectsPage() {
        return "subjects";
    }
}
