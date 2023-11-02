package com.agrotep.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/customError")
    public String handleCustomError() {
        // This method handles custom error requests
        return "redirect:/";
    }
}
