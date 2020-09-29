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

import java.util.HashSet;
import java.util.Set;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/")
    public String getMainPage(ModelMap model) {
        return "index";
    }


    @GetMapping(value = "/admin")
    public String listUsers(ModelMap model) {
        model.addAttribute("userInfo", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());
        model.addAttribute("listRoles", roleService.getAllRole());
        return "admin";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute User user, String[] posRoles) {
        userService.add(findUserRole(user, posRoles));
        return "redirect:/admin";
    }


    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute User user, String[] posRoles) {
        userService.updateUser(findUserRole(user, posRoles));
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@ModelAttribute User user) {
        userService.removeUser(user.getId());
        return "redirect:/admin";
    }


    @GetMapping("/user")
    public String infoUserPage(ModelMap model) {
        model.addAttribute("userInfo", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return "user";
    }

    private User findUserRole(User user, String[] posRoles) {
        Set<Role> userRoles = new HashSet<>();
        for (String role : posRoles) {
            userRoles.add(roleService.getRoleByName(role));
        }
        user.setRoles(userRoles);
        return user;
    }

}
