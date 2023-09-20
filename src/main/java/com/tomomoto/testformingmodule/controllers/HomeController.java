package com.tomomoto.testformingmodule.controllers;

import com.tomomoto.testformingmodule.entities.Reminder;
import com.tomomoto.testformingmodule.entities.User;
import com.tomomoto.testformingmodule.repos.ReminderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/home")
@SessionAttributes("user")
public class HomeController {
    private final ReminderRepository reminderRepo;

    @Autowired
    public HomeController(ReminderRepository reminderRepo) {
        this.reminderRepo = reminderRepo;
    }

    @ModelAttribute(name = "reminder")
    public Reminder getReminder() {
        return new Reminder();
    }

    @ModelAttribute(name = "user")
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/remove-reminder")
    public String removeReminder(Reminder reminder, User user) {
        reminder.setUser(user);
        log.info("Removing reminder:\nid - {}\nreminder text - {}", reminder.getId(), reminder.getReminderText());
        user.getReminderList().remove(reminder);
        reminderRepo.deleteById(reminder.getId());
        return "redirect:/home";
    }

    @PostMapping("/add-reminder")
    public String addReminder(@Valid Reminder reminder, Errors errors, @ModelAttribute User user) {
        if (errors.hasErrors()) {
            return "index";
        }

        log.info("Adding reminder:\nuser id - {}", user.getUserId());
        reminder.setUser(user);
        user.getReminderList().add(reminder);
        reminderRepo.save(reminder);

        return "redirect:/home";
    }
}
