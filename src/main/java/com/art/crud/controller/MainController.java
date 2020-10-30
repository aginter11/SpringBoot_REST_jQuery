package com.art.crud.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @GetMapping(value = "/admin")
    public String listUsers(ModelMap model) {
        model.addAttribute("userInfo", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return "admin";
    }

    @GetMapping(value = "/user")
    public String getUserPage(ModelMap model) {
        model.addAttribute("userInfo", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return "user";
    }

}
