package com.example.top10movies.controllers;


import com.example.top10movies.services.TopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {

    private TopService topService;

    public MainController(TopService topService) {
        this.topService = topService;
    }

    @GetMapping("/top10")
    public String getTopTenPage(@RequestParam(required = false) String dateTime, Model model){
        if (dateTime != null){
            model.addAttribute("movielist", topService.getTopTen(LocalDateTime.parse(dateTime + " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        }

        return "top10.html";
    }

}
