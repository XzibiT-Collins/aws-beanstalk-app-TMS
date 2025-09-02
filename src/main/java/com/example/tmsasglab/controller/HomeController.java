package com.example.tmsasglab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to Our Application");
        model.addAttribute("message", "This is a simple Spring Boot landing page!");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us");
        model.addAttribute("message", "Learn more about our application and services.");
        return "about";
    }
}
