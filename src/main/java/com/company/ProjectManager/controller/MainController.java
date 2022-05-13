package com.company.ProjectManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping()
    public String init() {
        return "Hi";
    }
}
