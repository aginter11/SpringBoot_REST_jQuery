package com.art.crud.controller;

import com.art.crud.model.Role;
import com.art.crud.model.User;
import com.art.crud.service.RoleService;
import com.art.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/admin")
    public String listUsers(ModelMap model) {
        model.addAttribute("userInfo", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());
        model.addAttribute("listRoles", roleService.getAllRole());
        return "admin";
    }


}
