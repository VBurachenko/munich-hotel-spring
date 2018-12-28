package com.burachenko.munichhotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/international")
    public String getInternationalPage() {
        return "home";
    }
}
