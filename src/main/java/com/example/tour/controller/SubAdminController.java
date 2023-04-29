package com.example.tour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subAdmin")
public class SubAdminController {

    @GetMapping("/tour")
    public String create() {
        return "subadmin";
    }
}
