package com.example.task2spring.controller;

import com.example.task2spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @RequestMapping("/bets")
    public String getBets(Model model) {
        model.addAttribute("games", service.getBets());
        return "bets";
    }
}
